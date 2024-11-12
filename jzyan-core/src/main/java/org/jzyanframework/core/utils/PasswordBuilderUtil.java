package org.jzyanframework.core.utils;

import java.util.Random;

/**
 * password util
 *
 * @author jzyan
 */
public class PasswordBuilderUtil {

    private final static char[] input = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*.?".toCharArray();

    private PasswordBuilderUtil() {
    }

    /**
     * create
     *
     * @param length
     * @return
     */
    public static String createPassword(Integer length) {
        String result = getRandomPassword(length);
        if (result.matches(".*[a-z]{1,}.*") && result.matches(".*[A-Z]{1,}.*") && result.matches(".*[0-9]{1,}.*") && result.matches(".*[~!@#$%^&*\\.?]{1,}.*")) {
            return result;
        }
        return createPassword(length);
    }

    /**
     * get password
     *
     * @param length
     * @return
     */
    public static String getRandomPassword(int length) {
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int x = 0; x < length; ++x) {
            sb.append(input[r.nextInt(input.length)]);
        }
        return sb.toString();
    }

}
