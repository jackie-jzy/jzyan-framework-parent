package org.jzyanframework.log.converter;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ObjectUtils;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 日志脱敏组件
 * </p>
 *
 * @author jzyan
 * @since 2023-02-20
 */
public class DesensitizationConverter extends MessageConverter {
    private static final int STR_LENGTH = 2000;
    private static String DEFAULT_FIELD_REGEX = "username|password|mobile|idCard|phone|email|smsCode";
    private static String loggingDestField;
    private static Pattern pattern;

    static {
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(new ClassPathResource("application.yml"));
        Properties properties = factoryBean.getObject();
        if (ObjectUtils.isEmpty(properties)) {
            factoryBean.setResources(new ClassPathResource("bootstrap.yml"));
            properties = factoryBean.getObject();
        }
        if (!ObjectUtils.isEmpty(properties)) {
            Object obj = properties.get("logging.dest.field");
            if (!ObjectUtils.isEmpty(obj)) {
                loggingDestField = obj.toString();
            }
        }
        // 初始化 pattern
        initPattern();
    }

    @Override
    public String convert(ILoggingEvent event) {
        String formattedMessage = event.getFormattedMessage();
        if (!ObjectUtils.isEmpty(formattedMessage)) {
            if (formattedMessage.length() > STR_LENGTH) {
                formattedMessage = formattedMessage.substring(0, STR_LENGTH) + "...";
            }
            Matcher match = pattern.matcher(formattedMessage);
            // 处理要打印的日志信息
            StringBuffer sbMsg = new StringBuffer();
            while (match.find()) {
                match.appendReplacement(sbMsg, match.group(1) + match.group(2) + replaceChar(match.group(3)));
            }
            // 增加最后一个匹配项后面的值
            match.appendTail(sbMsg);
            return sbMsg.toString();
        } else {
            return super.convert(event);
        }
    }

    /**
     * 字符替换
     *
     * @param value
     * @return
     */
    private String replaceChar(String value) {
        if (!ObjectUtils.isEmpty(value) && !"null".equals(value)) {
            if (value.contains("#")) {
                return lStr("", '*', value.length());
            }
            // value <= 6   替换为******
            if (value.length() <= 6) {
                return lStr("", '*', value.length());
                //后四位保留，其他为*
            } else if (value.length() > 6 && value.length() <= 12) {
                int valLen = value.length();
                String replaceHeadStr = value.substring(value.length() - 4);
                return lStr(replaceHeadStr, '*', valLen);
                //前4位保留  中间*  后4位保留
            } else {
                String replaceHeadStr = value.substring(0, 4);
                String replaceTailStr = value.substring(value.length() - 4);
                String dimStr = lStr("", '*', value.length() - 8);
                return replaceHeadStr + dimStr + replaceTailStr;
            }
        } else {
            return "";
        }
    }

    /**
     * 左补长char
     *
     * @param s
     * @param ch
     * @param width
     * @return
     */
    private String lStr(String s, char ch, int width) {
        // 需要前面补'0'
        if (s.length() < width) {
            while (s.length() < width) {
                s = ch + s;
            }
        } else { // 需要将高位丢弃
            s = s.substring(s.length() - width, s.length());
        }
        return s;
    }

    /**
     * 初始化 pattern
     */
    private static void initPattern() {
        StringBuffer regexBuff = new StringBuffer();
        if (!ObjectUtils.isEmpty(loggingDestField)) {
            if (!loggingDestField.startsWith("|")) {
                loggingDestField = "|" + loggingDestField;
            }
            regexBuff.append(DEFAULT_FIELD_REGEX);
            regexBuff.append(loggingDestField);
        } else {
            regexBuff.append(DEFAULT_FIELD_REGEX);
        }
        StringBuffer regexArrayBuff = new StringBuffer();
        String[] regexArray = regexBuff.toString().split("\\|");
        for (String regex : regexArray) {
            regexArrayBuff.append("|\"");
            regexArrayBuff.append(regex);
            regexArrayBuff.append("\"");
        }
        StringBuffer compileBuff = new StringBuffer();
        compileBuff.append("(");
        compileBuff.append(regexBuff.append(regexArrayBuff));
        compileBuff.append(")\\s*(:|=|：)\\s*([#\\s\\w\u4e00-\u9fa5\"\"]*)");
        pattern = Pattern.compile(compileBuff.toString());
    }

}
