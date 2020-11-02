package net.scode.commons.util;

import cn.hutool.core.util.ArrayUtil;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 汉语拼音处理，pinyin4j封装<br>
 * 基于https://sourceforge.net/projects/pinyin4j/
 *
 * @author tanghuang 2020年02月27日
 */
public class PinyinUtil {

    private static final Logger log = LoggerFactory.getLogger(PinyinUtil.class);

    /**
     * 创建汉语拼音处理类
     */
    private static HanyuPinyinOutputFormat defaultFormat;

    static {
        defaultFormat = new HanyuPinyinOutputFormat();
        // 输出设置，大小写，音标方式
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    /**
     * 将字符串转换成拼音数组
     *
     * @param src
     * @param isPolyphone 是否查出多音字的所有拼音
     * @param separator   多音字拼音之间的分隔符
     * @return
     */
    public static String[] spell(String src, boolean isPolyphone, String separator) {
        // 判断字符串是否为空
        if ("".equals(src) || null == src) {
            return null;
        }
        char[] srcChar = src.toCharArray();
        int srcCount = srcChar.length;
        String[] srcStr = new String[srcCount];

        for (int i = 0; i < srcCount; i++) {
            srcStr[i] = charToSpell(srcChar[i], isPolyphone, separator);
        }
        return srcStr;
    }

    /**
     * 将字符串转换成拼音数组
     *
     * @param src
     * @return
     */
    public static String[] spell(String src) {
        return spell(src, false, null);
    }

    /**
     * 将单个字符转换成拼音
     *
     * @param src
     * @param isPolyphone 是否显示多音字
     * @param separator   多音字分割符
     * @return
     */
    public static String charToSpell(char src, boolean isPolyphone, String separator) {
        StringBuilder tempPinying = new StringBuilder();
        // 如果是中文
        if (src > 128) {
            try {
                // 转换得出结果
                String[] strs = PinyinHelper.toHanyuPinyinStringArray(src, defaultFormat);
                if (ArrayUtil.isNotEmpty(strs)) {
                    // 是否查出多音字，默认是查出多音字的第一个字符
                    if (isPolyphone && null != separator) {
                        for (int i = 0; i < strs.length; i++) {
                            tempPinying.append(strs[i]);
                            if (strs.length != (i + 1)) {
                                // 多音字之间用特殊符号间隔起来
                                tempPinying.append(separator);
                            }
                        }
                    } else {
                        tempPinying.append(strs[0]);
                    }
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                log.error(e.getMessage());
            }
        } else {
            tempPinying.append(src);
        }
        return tempPinying.toString();
    }

    /**
     * 将单个字符取首字母
     *
     * @param src
     * @return
     */
    public static String firstSpell(char src) {
        String spell = charToSpell(src, false, null);
        if (StringUtil.isNotBlank(spell)) {
            return spell.substring(0, 1);
        }
        return "";
    }


    /**
     * 取首字母，即声母
     *
     * @param str
     * @return
     */
    public static String firstSpell(String str) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        return String.valueOf(firstSpell(str.charAt(0)));
    }

    /**
     * 返回字符串的首字母缩写,是汉字转化为首字母缩写,其它字符不进行转换
     *
     * @param str String 字符串
     * @return String 转换成缩写的字符串
     */
    public static String getAcronym(String str) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        char[] chars = str.toCharArray();
        StringBuilder ret = new StringBuilder();
        for (char c : chars) {
            ret.append(firstSpell(c));
        }
        return ret.toString();
    }

}
