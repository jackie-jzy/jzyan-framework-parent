package org.jzyanframework.core.utils;

import java.util.UUID;

/**
 * uuid
 *
 * @author jzyan
 */
public class UUIDUtil {

    private UUIDUtil() {

    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

}
