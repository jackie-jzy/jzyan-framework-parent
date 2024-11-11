package org.jzyanframework.core.utils;


import org.jzyanframework.core.enums.SHAEncryptTypeEnum;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>SHA加密工具类</p>
 *
 * @author jzyan
 * @version 2018/9/5
 */
public class SHAEncryptUtil {


    /**
     * SHA加密公共方法
     *
     * @param content 目标字符串
     * @param type    加密类型 {@link SHAEncryptTypeEnum}
     */
    public static String encrypt(String content, SHAEncryptTypeEnum type) {
        if (content == null || "".equals(content.trim())) {
            return "";
        }
        if (type == null) {
            type = SHAEncryptTypeEnum.SHA256;
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance(type.value);
            byte[] bytes = md5.digest((content).getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密字符串[" + content + "]时遇到异常", e);
        }
    }
}
