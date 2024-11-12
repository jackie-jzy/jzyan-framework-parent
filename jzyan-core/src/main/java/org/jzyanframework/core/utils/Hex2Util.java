package org.jzyanframework.core.utils;

/**
 * <p>二进制转换工具类</p>
 *
 * @author jzyan
 */
public class Hex2Util {

    private static final char[] HEX = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    /**
     * 二进位组转十六进制字符串
     *
     * @param buf 二进位组
     * @return 十六进制字符串
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 十六进制字符串转二进位组
     *
     * @param hexStr 十六进制字符串
     * @return 二进位组
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];

        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static char[] encode(byte[] bytes) {
        final int nBytes = bytes.length;
        char[] result = new char[2 * nBytes];
        int j = 0;
        for (int i = 0; i < nBytes; i++) {
            // Char for top 4 bits
            result[j++] = HEX[(0xF0 & bytes[i]) >>> 4];
            // Bottom 4
            result[j++] = HEX[(0x0F & bytes[i])];
        }
        return result;
    }

    public static byte[] decode(CharSequence s) {
        int nChars = s.length();
        if (nChars % 2 != 0) {
            throw new IllegalArgumentException("Hex-encoded string must have an even number of characters");
        }
        byte[] result = new byte[nChars / 2];
        for (int i = 0; i < nChars; i += 2) {
            int msb = Character.digit(s.charAt(i), 16);
            int lsb = Character.digit(s.charAt(i + 1), 16);
            if (msb < 0 || lsb < 0) {
                throw new IllegalArgumentException("Non-hex character in input: " + s);
            }
            result[i / 2] = (byte) ((msb << 4) | lsb);
        }
        return result;
    }
}
