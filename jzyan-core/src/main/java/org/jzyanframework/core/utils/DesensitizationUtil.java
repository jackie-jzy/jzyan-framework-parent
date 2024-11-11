package org.jzyanframework.core.utils;


import org.apache.commons.lang3.StringUtils;

/**
 * @Title: CommonUtil
 * @Date 2020年02月13日 14:11
 * @Description: 脱敏工具类
 */
public class DesensitizationUtil {
    private DesensitizationUtil() {
    }

    /**
     * 银行卡号脱敏
     *
     * @param bankCardNum
     * @return
     */
    public static String bankCardNumDesensitization(String bankCardNum) {
        checkParam(bankCardNum);
        if (bankCardNum.length() > 4) {
            return desensitizationSuffix(bankCardNum, "**** **** **** ", (bankCardNum.length() - 4));
        }
        return bankCardNum;
    }

    /**
     * 身份证号脱敏
     *
     * @param certNo
     * @return
     */
    public static String certNoDesensitization(String certNo) {
        checkParam(certNo);
        if (certNo.length() > 8) {
            return desensitizationPrefixAndSuffix(certNo, "**********", certNo.length() - 4, 4);
        }
        return certNo;
    }

    /**
     * 手机号脱敏
     *
     * @param mobile
     * @return
     */
    public static String mobileDesensitization(String mobile) {
        checkParam(mobile);
        if (mobile.length() == 11) {
            return desensitizationPrefixAndSuffix(mobile, "****", mobile.length() - 4, 3);
        }
        return mobile;
    }

    /**
     * 姓名脱敏
     *
     * @param name
     * @return
     */
    public static String nameDesensitization(String name) {
        checkParam(name);
        if (name.length() > 2) {
            return desensitizationSuffix(name, "*", 1);
        }
        return name;
    }

    /**
     * 基于字符串后缀 脱敏字符
     *
     * @param param
     * @param destStr    脱敏字符
     * @param beginIndex
     * @return
     */
    public static String desensitizationSuffix(String param, String destStr, int beginIndex) {
        checkParam(param);
        StringBuilder builder = new StringBuilder();
        String suffix = param.substring(beginIndex);
        builder.append(destStr);
        builder.append(suffix);
        return builder.toString();
    }

    /**
     * 基于字符串前缀 脱敏字符
     *
     * @param param
     * @param destStr    脱敏字符
     * @param beginIndex
     * @return
     */
    public static String desensitizationPrefix(String param, String destStr, int beginIndex) {
        checkParam(param);
        StringBuilder builder = new StringBuilder();
        String prefix = param.substring(beginIndex);
        builder.append(prefix);
        builder.append(destStr);
        return builder.toString();
    }

    /**
     * 基于字符串前后缀 脱敏字符
     *
     * @param param
     * @param destStr    脱敏字符
     * @param beginIndex
     * @param endIndex
     * @return
     */
    public static String desensitizationPrefixAndSuffix(String param, String destStr, int beginIndex, int endIndex) {
        checkParam(param);
        StringBuilder builder = new StringBuilder();
        String prefix = param.substring(0, endIndex);
        String suffix = param.substring(beginIndex);
        builder.append(prefix);
        builder.append(destStr);
        builder.append(suffix);
        return builder.toString();
    }

    private static void checkParam(String param) {
        if (!StringUtils.isNotBlank(param)) {
            throw new IllegalArgumentException("param can not be null");
        }
    }
}
