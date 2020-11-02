package net.scode.commons.core;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一个有int值和对应命名的枚举
 *
 * @author tanghuang 2020年02月18日
 */
public interface IdNameEnum extends IntEnum {

    String getName();

    Map<Class, Map> NAME_ENUM_MAP = new HashMap<>();

    /**
     * 根据name获得对应枚举
     *
     * @param name
     * @param clazz
     * @param <E>
     * @return
     */
    static <E extends Enum & IdNameEnum> E fromName(String name, Class<E> clazz, E defaultEnum) {
        Map enumMap = NAME_ENUM_MAP.get(clazz);
        if (null == enumMap) {
            E[] enums = clazz.getEnumConstants();
            enumMap = new HashMap<String, E>();
            for (E current : enums) {
                enumMap.put(current.getName(), current);
            }
        }
        E result = (E) enumMap.get(name);
        if (result == null) {
            return defaultEnum;
        }
        return result;
    }

    /**
     * 转成map列表
     *
     * @return
     */
    static <E extends Enum & IdNameEnum> List<Map<String, Object>> toMapList(Class<E> clazz) {
        E[] enums = clazz.getEnumConstants();
        if (null == enums) {
            return null;
        }
        List<Map<String, Object>> list = CollectionUtil.newArrayList();
        for (E current : enums) {
            Map<String, Object> tempMap = MapUtil.newHashMap();
            tempMap.put("value", current.getValue());
            tempMap.put("name", current.getName());
            list.add(tempMap);
        }
        return list;
    }

}
