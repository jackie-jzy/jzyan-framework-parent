package org.jzyan.framework.core.utils;

import java.util.Random;

/**
 * @ProjectName : online-shop
 * @FileName : PasswordBuilderUtil
 * @Version : 1.0
 * @Package : com.juzifenqi.onshop.utils.encrypt
 * @Description : 登录用户密码生成器
 * @Author : jzyan
 * @CreateDate : 2019/04/15 15:33
 * @ModificationHistory Who        When      What
 * --------- ---------     ---------------------------
 */
public class PasswordBuilderUtil {

    private final static char[] input = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*.?".toCharArray();

    private PasswordBuilderUtil() {
    }

    public static String createPassword(Integer length) {
        String result = getRandomPassword(length);
        if (result.matches(".*[a-z]{1,}.*") && result.matches(".*[A-Z]{1,}.*") && result.matches(".*[0-9]{1,}.*") && result.matches(".*[~!@#$%^&*\\.?]{1,}.*")) {
            return result;
        }
        return createPassword(length);
    }

    public static String getRandomPassword(int length) {
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int x = 0; x < length; ++x) {
            sb.append(input[r.nextInt(input.length)]);
        }
        return sb.toString();
    }

}
