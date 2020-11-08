package com.what.to.eat.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.what.to.eat.server.dao.User_sessionDao;
import com.what.to.eat.server.po.User;
import com.what.to.eat.server.po.User_session;
import com.what.to.eat.server.service.User_sessionService;
import org.springframework.stereotype.Service;

/**
 * User_session对应service实现
 *
 * @author auto 2020年11月05日
 */
@Service
public class User_sessionServiceImpl extends ServiceImpl<User_sessionDao,User_session> implements User_sessionService {



    @Override
    public String getSessionKeyByOpenid(String openid) {
        QueryWrapper<User_session> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        User_session user_session = getOne(queryWrapper);
        return user_session.getSessionKey();
    }

    @Override
    public void saveSessionKey(String openid, String sessionKey) {
        QueryWrapper<User_session> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        User_session user_session = getOne(queryWrapper);
        if (user_session != null) {
            user_session.setSessionKey(sessionKey);
            update(user_session, new QueryWrapper<User_session>().eq("openid", openid));
        } else {
            user_session = new User_session();
            user_session.setOpenid(openid);
            user_session.setSessionKey(sessionKey);
            save(user_session);
        }
    }
}
