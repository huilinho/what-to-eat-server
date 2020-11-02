package net.scode.commons.util;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 字符串处理测试
 *
 * @author tanghuang 2020年02月24日
 */
public class StringUtilTest {

    @Test
    public void isBlankTest() {
        String blank = "	  　";
        Assert.assertTrue(StringUtil.isBlank(blank));
    }

    @Test
    public void isBlankTest2() {
        String blank = "\u202a";
        Assert.assertTrue(StringUtil.isBlank(blank));
    }

    @Test
    public void trimTest() {
        String blank = "	 哈哈 　";
        String trim = StringUtil.trim(blank);
        Assert.assertEquals("哈哈", trim);
    }

    @Test
    public void cleanBlankTest() {
        // 包含：制表符、英文空格、不间断空白符、全角空格
        String str = "	 你 好　";
        String cleanBlank = StringUtil.cleanBlank(str);
        Assert.assertEquals("你好", cleanBlank);
    }

    @Test
    public void cutTest() {
        String str = "aaabbbcccdddaadfdfsdfsdf0";
        String[] cut = StringUtil.cut(str, 4);
        Assert.assertArrayEquals(new String[]{"aaab", "bbcc", "cddd", "aadf", "dfsd", "fsdf", "0"}, cut);
    }

    @Test
    public void splitTest() {
        String str = "a,b ,c,d,,e";
        List<String> split = StringUtil.split(str, ',', -1, true, true);
        // 测试空是否被去掉
        Assert.assertEquals(5, split.size());
        // 测试去掉两边空白符是否生效
        Assert.assertEquals("b", split.get(1));

        final String[] strings = StringUtil.splitToArray("abc/", '/');
        Assert.assertEquals(2, strings.length);
    }

    @Test
    public void splitToLongTest() {
        String str = "1,2,3,4, 5";
        long[] longArray = StringUtil.splitToLong(str, ',');
        Assert.assertArrayEquals(new long[]{1, 2, 3, 4, 5}, longArray);

        longArray = StringUtil.splitToLong(str, ",");
        Assert.assertArrayEquals(new long[]{1, 2, 3, 4, 5}, longArray);
    }

    @Test
    public void splitToIntTest() {
        String str = "1,2,3,4, 5";
        int[] intArray = StringUtil.splitToInt(str, ',');
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, intArray);

        intArray = StringUtil.splitToInt(str, ",");
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, intArray);
    }

    @Test
    public void formatTest() {
        String template = "你好，我是{name}，我的电话是：{phone}";
        String result = StringUtil.format(template, Dict.create().set("name", "张三").set("phone", "13888881111"));
        Assert.assertEquals("你好，我是张三，我的电话是：13888881111", result);

        String result2 = StringUtil.format(template, Dict.create().set("name", "张三").set("phone", null));
        Assert.assertEquals("你好，我是张三，我的电话是：{phone}", result2);
    }

    @Test
    public void replaceTest() {
        String string = StrUtil.replace("aabbccdd", 2, 6, '*');
        Assert.assertEquals("aa****dd", string);
        string = StrUtil.replace("aabbccdd", 2, 12, '*');
        Assert.assertEquals("aa******", string);

        String result = StrUtil.replace("123", "2", "3");
        Assert.assertEquals("133", result);

        result = StrUtil.replace(",abcdef,", ",", "|");
        Assert.assertEquals("|abcdef|", result);

        String a = "1039";
        result = StrUtil.padPre(a, 8, "0"); //在字符串1039前补4个0
        Assert.assertEquals("00001039", result);
    }

    @Test
    public void upperFirstTest() {
        StringBuilder sb = new StringBuilder("KEY");
        String s = StrUtil.upperFirst(sb);
        Assert.assertEquals(s, sb.toString());
    }

    @Test
    public void lowerFirstTest() {
        StringBuilder sb = new StringBuilder("KEY");
        String s = StrUtil.lowerFirst(sb);
        Assert.assertEquals("kEY", s);
    }

    @Test
    public void subTest() {
        String a = "abcderghigh";
        String pre = StrUtil.sub(a, -5, a.length());
        Assert.assertEquals("ghigh", pre);
    }

    @Test
    public void toCamelCaseTest() {
        String str = "Table_Test_Of_day";
        String result = StrUtil.toCamelCase(str);
        Assert.assertEquals("tableTestOfDay", result);

        String str1 = "TableTestOfDay";
        String result1 = StrUtil.toCamelCase(str1);
        Assert.assertEquals("TableTestOfDay", result1);
    }

    @Test
    public void toUnderLineCaseTest() {
        String str = "Table_Test_Of_day";
        String result = StrUtil.toUnderlineCase(str);
        Assert.assertEquals("table_test_of_day", result);

        String str1 = "_Table_Test_Of_day_";
        String result1 = StrUtil.toUnderlineCase(str1);
        Assert.assertEquals("_table_test_of_day_", result1);

        String str2 = "_Table_Test_Of_DAY_";
        String result2 = StrUtil.toUnderlineCase(str2);
        Assert.assertEquals("_table_test_of_DAY_", result2);

        String str3 = "_TableTestOfDAYtoday";
        String result3 = StrUtil.toUnderlineCase(str3);
        Assert.assertEquals("_table_test_of_DAY_today", result3);

        String str4 = "HelloWorld_test";
        String result4 = StrUtil.toUnderlineCase(str4);
        Assert.assertEquals("hello_world_test", result4);
    }

}
