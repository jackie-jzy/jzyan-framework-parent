package org.jzyan.framework.core.utils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.regex.Pattern;

/**
 * @FileName : StringUtil
 * @Version : 1.0
 * @Description : 字符串工具类
 * @Author : jzyan
 * @CreateDate : 2020/11/10 17:05
 */
public class StringUtil {

    private StringUtil() {
    }

    /**
     * 连接器[Joiner]
     *
     * @param symbol 连接符
     * @param parts  参数
     * @return
     */
    public static String joinerSkipNulls(String symbol, Object[] parts) {
        Joiner joiner = Joiner.on(symbol).skipNulls();
        return joiner.join(parts);
    }

    /**
     * 连接器[Joiner]
     *
     * @param symbol 连接符
     * @param parts  参数
     * @return
     */
    public static String joinerSkipNulls(String symbol, Iterable<?> parts) {
        Joiner joiner = Joiner.on(symbol).skipNulls();
        return joiner.join(parts);
    }

    /**
     * 拆分器[Splitter]
     *
     * @param symbol 拆分符
     * @param param  参数
     * @return
     */
    public static Iterable<String> splitter(String symbol, String param) {
        Splitter splitter = Splitter.on(symbol).trimResults().omitEmptyStrings();
        return splitter.split(param);
    }

    /**
     * 拆分器[Splitter]
     *
     * @param symbol 拆分符
     * @param param  参数
     * @return
     */
    public static Iterable<String> splitter(char symbol, String param) {
        Splitter splitter = Splitter.on(symbol).trimResults().omitEmptyStrings();
        return splitter.split(param);
    }

    /**
     * 拆分器[Splitter]
     *
     * @param pattern 拆分符 正则表达式
     * @param param   参数
     * @return
     */
    public static Iterable<String> splitter(Pattern pattern, String param) {
        Splitter splitter = Splitter.on(pattern).trimResults().omitEmptyStrings();
        return splitter.split(param);
    }

}
