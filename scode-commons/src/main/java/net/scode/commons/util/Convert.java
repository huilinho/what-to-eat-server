package net.scode.commons.util;

import net.scode.commons.constant.Consts;

/**
 * 类型转换<br>
 * 扩展了hutool的Convert
 *
 * @author tanghuang 2020年02月22日
 */
public class Convert extends cn.hutool.core.convert.Convert {

    /**
     * 对象为{@code null}时取默认值
     *
     * @param reference
     * @param defaultVal
     * @return
     */
    public static <T> T notNull(final T reference, final T defaultVal) {
        return reference == null ? defaultVal : reference;
    }

    /**
     * 取不为空的字符串为null时返回空串
     *
     * @param s 待处理的字符串
     * @return
     */
    public static String notNull(final String s) {
        return notNull(s, Consts.EMPTY);
    }

}
