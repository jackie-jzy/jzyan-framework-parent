package org.jzyan.framework.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.jzyan.framework.interceptor.context.AuthContext;
import org.springframework.stereotype.Component;

/**
 * @Author : jzyan
 * @CreateDate : 2020/11/19 11:14
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createId", Long.class, AuthContext.getUserId());
        this.strictInsertFill(metaObject, "updateId", Long.class, AuthContext.getUserId());
        this.strictInsertFill(metaObject, "createUser", String.class, AuthContext.getUsername());
        this.strictInsertFill(metaObject, "updateUser", String.class, AuthContext.getUsername());
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updateUser", String.class, AuthContext.getUsername());
        this.strictInsertFill(metaObject, "updateId", Long.class, AuthContext.getUserId());
    }

}
