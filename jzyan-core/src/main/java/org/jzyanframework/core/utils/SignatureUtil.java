package org.jzyanframework.core.utils;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.util.*;

/**
 * 签名工具类，按字母升序排列，拼接key
 *
 * @author jzyan
 */
public class SignatureUtil {
    private static BouncyCastleProvider provider = new BouncyCastleProvider();

    /**
     * 直接拼接key在最后面
     * 例：key=123456789的时候待签名的字符串为，aa=3&bb=1&cc=1&dd=20160426999123456789
     *
     * @param partnerKey
     * @param params
     * @return
     */
    public static String signParams(String partnerKey, Map params) {
        //直接拼接key
        String tobeSign = getToBeSign(params) + partnerKey;
        return md5dataToSign(tobeSign);
    }


    public static String md5dataToSign(String data) {
        String algorithm = "MD5";
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm, provider);
            byte[] rtn = md.digest(data.getBytes("UTF-8"));
            return byte2Hex(rtn).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("不支持的算法:" + algorithm);
        }
    }

    public static String getToBeSign(Map params) {
        TreeMap tm = new TreeMap();
        //参数排序
        tm.putAll(params);
        Set set = tm.entrySet();
        StringBuffer sb = new StringBuffer();
        int i = 0;
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry e = (Map.Entry) iterator.next();
            String k = (String) e.getKey();
            if (e.getValue() != null && !"".equals(e.getValue()) && !k.equalsIgnoreCase("plain")
                    && !"Signature".equals(k) && !"null".equals(e.getValue()) && !"sign".equals(k)) {
                String v;
                if (e.getValue() instanceof JSONArray) {
                    v = getJsonArrayStr((JSONArray) e.getValue());
                } else if (e.getValue() instanceof JSONObject) {
                    v = getToBeSign((JSONObject) e.getValue());
                } else {
                    v = e.getValue().toString();
                }
                if (i != 0) {
                    sb.append("&");
                }
                sb.append(k + "=" + v);
                ++i;
            }
        }
        return sb.toString();
    }

    public static String getJsonArrayStr(JSONArray a) {
        String r = "";
        for (int j = 0; j < a.size(); j++) {
            Object b = a.get(j);
            String v;
            if (b instanceof JSONArray) {
                v = getJsonArrayStr((JSONArray) b);
            } else if (b instanceof JSONObject) {
                v = getToBeSign((JSONObject) b);
            } else {
                v = b.toString();
            }
            if (j == 0) {
                r = v;
            } else {
                r += "&" + v;
            }
        }
        return r;
    }

    public static String byte2Hex(byte[] b) {
        StringBuilder sb = new StringBuilder();
        String stmp;
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                sb.append("0").append(stmp);
            } else {
                sb.append(stmp);
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 验签
     *
     * @param accessKey 解密秘钥
     * @param map       响应的加密数据
     * @return
     */
    public static boolean verifySignature(String accessKey, Map map) {
        String resultSign = (String) map.get("sign");
        String generateSign = SignatureUtil.signParams(accessKey, map);
        return Objects.equals(resultSign, generateSign);
    }

}
