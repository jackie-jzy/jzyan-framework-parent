package org.jzyanframework.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * TID
 *
 * @author : jzyan
 */
public class TIdUtil {
    private TIdUtil() {
    }

    /**
     * 获取通信流水TID
     *
     * @return
     */
    public static String getTId() {
        int length = 16;
        String strHostId = "TID";
        length = length - 6 - strHostId.length();
        String uuid = UUID.randomUUID().toString().substring(0, length);
        return String.format("%s%s%s", strHostId, new SimpleDateFormat("HHmmss").format(new Date()), uuid);
    }

}



