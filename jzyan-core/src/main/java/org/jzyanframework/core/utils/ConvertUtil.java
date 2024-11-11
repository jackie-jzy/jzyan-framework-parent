package org.jzyanframework.core.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @date 2020/12/23 15:31
 */
@Slf4j
public class ConvertUtil {
    /**
     * 功能描述：浅拷贝 - 转换Bean对象
     *
     * @param sourceObject
     * @param clazz
     * @return
     * @author Elivense White
     */
    public static <T> T convert(Object sourceObject, Class<T> clazz) {
        T result = null;
        if (ObjectUtils.isNotEmpty(sourceObject)) {
            try {
                result = clazz.newInstance();
            } catch (Exception e) {
                log.error("convert error", e);
            }
            BeanUtils.copyProperties(sourceObject, result);
        }
        return result;
    }


    /**
     * 功能描述：浅拷贝 - 转换List<bean> 对象
     *
     * @param sourceList
     * @param clazz
     * @return
     * @author Elivense White
     */
    public static <T> List<T> convert(List<?> sourceList, Class<T> clazz) {
        List<T> resultList = new ArrayList<>();
        try {
            if (!CollectionUtils.isEmpty(sourceList)) {
                Iterator<?> iterator = sourceList.iterator();
                while (iterator.hasNext()) {
                    T t = clazz.newInstance();
                    Object sourceObject = iterator.next();
                    BeanUtils.copyProperties(sourceObject, t);
                    resultList.add(t);
                }
            }
        } catch (Exception e) {
            log.error("convert error", e);
        }
        return resultList;
    }

    /**
     * 将对象装换为map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (ObjectUtils.isNotEmpty(bean)) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     *
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     *
     * @param objList
     * @return
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(objList)) {
            Map<String, Object> map;
            T bean;
            for (int i = 0, size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     *
     * @param maps
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz)
            throws InstantiationException, IllegalAccessException {
        List<T> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(maps)) {
            Map<String, Object> map;
            T bean;
            for (int i = 0, size = maps.size(); i < size; i++) {
                map = maps.get(i);
                bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }

}
