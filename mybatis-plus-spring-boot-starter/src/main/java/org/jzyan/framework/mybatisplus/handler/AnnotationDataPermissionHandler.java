package org.jzyan.framework.mybatisplus.handler;

import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.jzyan.framework.core.constant.DataPermissionField;
import org.jzyan.framework.interceptor.context.AuthContext;
import org.jzyan.framework.mybatisplus.annotation.DataColumn;
import org.jzyan.framework.mybatisplus.annotation.DataScope;
import org.jzyan.framework.mybatisplus.exception.DataPermissionException;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * <p>
 * 数据权限处理器
 * </p>
 *
 * @author jzyan
 * @since 2022-03-14
 */
@Slf4j
public class AnnotationDataPermissionHandler implements DataPermissionHandler {

    private static final String GLIDE_LINE = "_";

    /**
     * 获取数据权限 SQL 片段
     *
     * @param where             待执行 SQL Where 条件表达式
     * @param mappedStatementId Mybatis MappedStatement Id 根据该参数可以判断具体执行方法
     * @return JSqlParser 条件表达式
     */
    @SneakyThrows
    @Override
    public Expression getSqlSegment(Expression where, String mappedStatementId) {
        // 超级管理员忽略数据权限
        if (AuthContext.isRoot()) {
            return where;
        }

        // 没有注解忽略数据权限
        DataScope annotation = getDataScope(mappedStatementId);
        if (ObjectUtils.isEmpty(annotation)) {
            return where;
        }

        //解析权限字段注解
        Map<String, Object> map = getEqualsTos(annotation.value());

        //拼接where sql
        AndExpression expression = new AndExpression(where, getAndExpression(map));
        return expression;
    }


    /**
     * 拼接where sql
     *
     * @param map
     * @return
     */
    private Expression getAndExpression(Map<String, Object> map) throws JSQLParserException {
        StringBuilder buffer = new StringBuilder();
        StringJoiner stringJoiner = new StringJoiner(" AND ");
        for (String key : map.keySet()) {
            buffer.setLength(0);
            stringJoiner.add(buffer.append(key).append(" = ").append(map.get(key)).toString());
        }
        Expression expression = CCJSqlParserUtil.parseCondExpression(stringJoiner.toString());
        return expression;
    }

    /**
     * 解析注解
     *
     * @param dataColumns
     * @return
     */
    private Map<String, Object> getEqualsTos(DataColumn[] dataColumns) {
        if (dataColumns == null || dataColumns.length == 0) {
            throw new DataPermissionException("数据权限注解@DataColumn不能为null");
        }

        Map<String, Object> map = new HashMap<>();
        StringBuilder buffer = new StringBuilder();
        for (DataColumn dataColumn : dataColumns) {
            if (StringUtils.isBlank(dataColumn.name())) {
                throw new DataPermissionException("数据权限注解@DataColumn.name不能为null");
            }
            buffer.setLength(0);
            if (StringUtils.isBlank(dataColumn.alias())) {
                switch (dataColumn.name()) {
                    case DataPermissionField.USER_ID:
                        map.put(dataColumn.name(), AuthContext.getUserId());
                        break;
                    case DataPermissionField.ORG_Id:
                        map.put(dataColumn.name(), AuthContext.getOrgId());
                        break;
                    case DataPermissionField.TENANT_ID:
                        map.put(dataColumn.name(), AuthContext.getTenantId());
                        break;
                    default:
                        throw new DataPermissionException("数据权限不支持该字段：" + dataColumn.name());
                }
            } else {
                switch (dataColumn.name()) {
                    case DataPermissionField.USER_ID:
                        map.put(getAliasKey(buffer, dataColumn), AuthContext.getUserId());
                        break;
                    case DataPermissionField.ORG_Id:
                        map.put(getAliasKey(buffer, dataColumn), AuthContext.getOrgId());
                        break;
                    case DataPermissionField.TENANT_ID:
                        map.put(getAliasKey(buffer, dataColumn), AuthContext.getTenantId());
                        break;
                    default:
                        throw new DataPermissionException("数据权限不支持该字段：" + dataColumn.name());
                }
            }
        }
        return map;
    }

    private String getAliasKey(StringBuilder buffer, DataColumn dataColumn) {
        return buffer
                .append(dataColumn.alias())
                .append(".")
                .append(dataColumn.name())
                .toString();
    }

    /**
     * 获取注解
     *
     * @param mappedStatementId
     * @return
     * @throws ClassNotFoundException
     */
    private DataScope getDataScope(String mappedStatementId) {
        DataScope annotation = null;
        try {
            Class<?> clazz = Class.forName(mappedStatementId.substring(0, mappedStatementId.lastIndexOf(".")));
            String methodStr = mappedStatementId.substring(mappedStatementId.lastIndexOf(".") + 1);
            if (methodStr.contains(GLIDE_LINE)) {
                methodStr = methodStr.split(GLIDE_LINE)[0];
            }
            Method[] methods = clazz.getMethods();
            Map<String, DataScope> map = new HashMap<>();
            for (Method method : methods) {
                DataScope dataScope = method.getAnnotation(DataScope.class);
                if (!ObjectUtils.isEmpty(dataScope)) {
                    map.put(method.getName(), dataScope);
                }
            }
            annotation = map.get(methodStr);
        } catch (ClassNotFoundException e) {
        } catch (Exception e) {
            throw e;
        }
        return annotation;
    }

}
