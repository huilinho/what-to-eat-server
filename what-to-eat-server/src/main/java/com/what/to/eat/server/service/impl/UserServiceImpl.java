package com.what.to.eat.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.what.to.eat.server.dao.UserDao;
import com.what.to.eat.server.po.User;
import com.what.to.eat.server.service.UserService;
import org.springframework.stereotype.Service;

/**
 * User对应service实现
 * 
 * @author auto 2020年11月02日 
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {

}