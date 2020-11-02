package net.scode.commons.hutool;

import cn.hutool.json.JSONUtil;
import net.scode.commons.constant.Consts;
import net.scode.commons.core.R;
import org.junit.Test;

/**
 * Json测试
 *
 * @author tanghuang 2020年02月24日
 */
public class JsonTest {

    /**
     * 测试R转换json字符串
     */
    @Test
    public void testR() {
        R r = new R();
        System.out.println("page:" + JSONUtil.toJsonStr(r));
    }

    /**
     * 测试字符串转对象
     */
    @Test
    public void testFromStr() {
        String str = "{\"msg\":\"success\",\"code\":0}";
        R r = JSONUtil.toBean(str, R.class);
        System.out.println("r.msg:" + r.get(Consts.RESULT_MSG));
    }

}
