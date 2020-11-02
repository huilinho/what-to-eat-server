package com.what.to.eat.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.what.to.eat.server.dao.AdminDao;
import com.what.to.eat.server.po.Admin;
import com.what.to.eat.server.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * Admin对应service实现
 *
 * @author auto 2020年11月01日
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao,Admin> implements AdminService {

}
