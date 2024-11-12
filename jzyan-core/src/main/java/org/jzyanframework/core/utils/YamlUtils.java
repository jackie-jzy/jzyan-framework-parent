package org.jzyanframework.core.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * yaml util
 *
 * @author jzyan
 */
public class YamlUtils {
    private static final Logger log = LoggerFactory.getLogger(YamlUtils.class);
    //默认配置文件
    private static String defaultConfigFile = "config.yml";
    //当前结果对应的文件名(用于区别多次读取不同文件内容的情况,不至于每次都要重新读取文件)
    private static String currentConfigFile = null;
    //读取结果
    private static Map<String, String> result = null;

    /**
     * 以指定class为定位,获取制定文件名的配置文件,并读取
     *
     * @param clazz    类标识文件为空默认使用YamlUtils类本身作为标识
     * @param fileName 文件名为空,读取默认配置文件
     * @return
     */
    public static Map<String, String> getYamlByFileName(Class<?> clazz, String fileName) {
        result = new HashMap<>();
        try {
            if (clazz == null) {
                clazz = YamlUtils.class;
            }
            if (StringUtils.isBlank(fileName)) {
                fileName = defaultConfigFile;
            }
            currentConfigFile = fileName;
            log.info("加载配置文件:{}", fileName);
            Yaml yaml = new Yaml();
            InputStream is = clazz.getClassLoader().getResourceAsStream(fileName);
            Map<String, Object> params = yaml.loadAs(is, Map.class);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    eachYaml(entry.getKey(), (Map<String, Object>) entry.getValue());
                } else {
                    if (ObjectUtils.isNotEmpty(entry.getValue())) {
                        result.put(entry.getKey(), entry.getValue().toString());
                    }
                }
            }
        } catch (Exception e) {
            log.error("解析yml文件异常:", e);
        }
        return result;
    }

    /**
     * 使用递归进行深度转换,将Map<String,Object>转换为Map<String,String>;
     *
     * @param key 父级key
     * @param map 父级entry
     */
    private static void eachYaml(String key, Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String newKey = "";
            if (StringUtils.isNotEmpty(key)) {
                newKey = (key + "." + entry.getKey());
            } else {
                newKey = entry.getKey();
            }
            if (entry.getValue() instanceof Map) {
                eachYaml(newKey, (Map<String, Object>) entry.getValue());
            } else {
                if (ObjectUtils.isNotEmpty(entry.getValue())) {
                    result.put(newKey, entry.getValue().toString());
                }
            }
        }
    }

    /**
     * 根据key 获取指定的值(默认文件)
     */
    public static String getValue(String key) {
        if (result == null || result.size() <= 0 || currentConfigFile != defaultConfigFile) {
            getYamlByFileName(null, null);
        }
        if (result == null || StringUtils.isBlank(result.get(key))) {
            return null;
        }
        return result.get(key);
    }

    /**
     * 根据key 获取指定的值(指定文件)
     */
    public static String getValue(Class<?> clazz, String fileName, String key) {
        if (result == null || result.size() <= 0 || currentConfigFile != fileName) {
            getYamlByFileName(clazz, fileName);
        }
        if (result == null || StringUtils.isBlank(result.get(key))) {
            return null;
        }
        return result.get(key);
    }

}
