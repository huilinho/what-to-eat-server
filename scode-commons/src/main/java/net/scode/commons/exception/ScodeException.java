package net.scode.commons.exception;

import net.scode.commons.constant.Consts;

/**
 * scode异常类
 *
 * @author tanghuang 2020年02月18日
 */
public class ScodeException extends Exception {

    /**
     * 错误代码
     */
    private int code;

    public ScodeException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ScodeException(Exception e) {
        super(e);
        this.code = Consts.ERROR_CODE;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
