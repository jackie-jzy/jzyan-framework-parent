package org.jzyanframework.core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 对参数url字符串以utf-8编码将除字母和数字以及*字符外的都编码成%xx形式。
 *
 * @author jzyan
 */
public class UrlUtil {

    public static String urlEncode(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("URLEncoder[" + url + "]时遇到异常", e);
        }
    }

    /**
     * 将经过urlEncode处理的%xx形式的编码还原成普通字符串。
     *
     * @param url
     * @return
     */
    public static String urlDecode(String url) {
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("URLDecoder[" + url + "]时遇到异常", e);
        }
    }
}
