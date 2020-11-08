package com.what.to.eat.server.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.what.to.eat.server.dao.UserDao;
import com.what.to.eat.server.enums.DataStatus;
import com.what.to.eat.server.po.User;
import com.what.to.eat.server.service.UserService;
import com.what.to.eat.server.weixin.bean.WeixinUserInfo;
import org.springframework.stereotype.Service;

/**
 * User对应service实现
 *
 * @author auto 2020年11月02日
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {

    @Override
    public User addUser(WeixinUserInfo weixinUserInfo) {
        if (weixinUserInfo == null) {
            return null;
        }
        User user = getOne(new QueryWrapper<User>().eq("openid", weixinUserInfo.getOpenId()));
        if (user == null) {
            user = new User();
            user.setUsername(weixinUserInfo.getNickName());
            user.setOpenid(weixinUserInfo.getOpenId());
            user.setDataStatus(DataStatus.NORMAL.getValue());
            user.setCreateTime(DateUtil.date());
        }
        user.setAvatar(weixinUserInfo.getAvatarUrl());
        saveOrUpdate(user);
        return user;
    }
}
