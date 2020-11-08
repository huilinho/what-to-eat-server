package com.what.to.eat.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.what.to.eat.server.po.User;
import com.what.to.eat.server.weixin.bean.WeixinUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User对应Service
 *
 * @author auto 2020年11月02日
 */
public interface UserService extends IService<User> {

    public User addUser(WeixinUserInfo weixinUserInfo);
}
