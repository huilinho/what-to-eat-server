package net.scode.commons.hutool;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import org.junit.Test;

/**
 * 加解密测试
 *
 * @author tanghuang 2020年02月24日
 */
public class SecureUtilTest {

    /**
     * md5加密
     */
    @Test
    public void testMD5() {
        String str = "autmall.666";
        String md5 = SecureUtil.md5(str);
        System.out.println("md5:" + md5);
        System.out.println("md5.isEquals:" + md5.equals(SecureUtil.md5(str)));
    }

    /**
     * aes加解密
     */
    @Test
    public void testAes() {
        String str = "autmall.666";
        // aes的key必须为16、24、32字节
        String key = "abcd1234abcd1234";
        AES aes = SecureUtil.aes(key.getBytes());
        String aesBase64Code = aes.encryptBase64(str);
        System.out.println("aesBase64Code:" + aesBase64Code);
        String sourceStr = aes.decryptStr(aesBase64Code);
        System.out.println("sourceStr:" + sourceStr);
    }

}
