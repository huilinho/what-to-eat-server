package net.scode.commons.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 一个有int值的枚举
 *
 * @author tanghuang 2020年02月18日
 */
public interface IntEnum extends Serializable {

    int getValue();

    Map<Class, Map> ENUM_MAP = new HashMap<>();

    /**
     * 根据value获得对应枚举
     *
     * @param value
     * @param clazz
     * @param <E>
     * @return
     */
    static <E extends Enum & IntEnum> E fromValue(int value, Class<E> clazz, E defaultEnum) {
        Map enumMap = ENUM_MAP.get(clazz);
        if (null == enumMap) {
            E[] enums = clazz.getEnumConstants();
            enumMap = new HashMap<String, E>();
            for (E current : enums) {
                enumMap.put(current.getValue(), current);
            }
        }
        E result = (E) enumMap.get(value);
        if (result == null) {
            return defaultEnum;
        }
        return result;
    }

}
