package com.what.to.eat.server.consts;

/**
 * 权限枚举
 *
 * @author liuyoubin
 * @since 2020/4/7 - 0:14
 */
public enum PermissionsEnum {

    /**
     * 管理员删除权限
     */
    ADMIN_DELETE("admin:delete"),
    /**
     * 管理员禁止登陆权限
     */
    ADMIN_FORBID("admin:forbid");


    /**
     * 权限名
     */
    String permit;

    PermissionsEnum(String permit) {
        this.permit = permit;
    }

    public String getPermit() {
        return permit;
    }

}
