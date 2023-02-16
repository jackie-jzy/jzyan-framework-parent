package org.jzyan.framework.core.utils;

import java.util.UUID;

public class UUIDUtil {

    private UUIDUtil() {

    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

}
