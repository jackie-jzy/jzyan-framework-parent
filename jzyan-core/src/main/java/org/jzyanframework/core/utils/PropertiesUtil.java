package org.jzyanframework.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * proper util
 *
 * @author jzyan
 */
public class PropertiesUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    private static Lock lock = new ReentrantLock();
    private static Map<String, PropertiesUtil> instance = new ConcurrentHashMap();
    private ResourceBundle resourceBundle = null;

    private PropertiesUtil(String fileProperties) {
        this.loadingProperties(fileProperties);
    }

    private void loadingProperties(String fileProperties) {
        lock.lock();
        try {
            this.resourceBundle = ResourceBundle.getBundle(fileProperties);
        } catch (Exception e) {
            logger.error("load file" + fileProperties + " throw exception:", e);
        } finally {
            lock.unlock();
        }

    }

    public static PropertiesUtil getInstance(String fileProperties) {
        fileProperties = fileProperties.replace(".properties", "");
        PropertiesUtil config;
        synchronized (PropertiesUtil.class) {
            config = instance.get(fileProperties);
            if (config == null) {
                config = new PropertiesUtil(fileProperties);
                instance.put(fileProperties, config);
            }
            return config;
        }
    }

    public String getString(String paramString) {
        String result = "";
        try {
            result = this.resourceBundle.getString(paramString);
        } catch (Exception e) {
            logger.error("properties get value exception:", e);
        }
        return result;
    }

    public Enumeration<String> getKeys() {
        return this.resourceBundle.getKeys();
    }
}
