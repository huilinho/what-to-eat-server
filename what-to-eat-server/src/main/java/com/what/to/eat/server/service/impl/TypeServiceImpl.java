package com.what.to.eat.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.what.to.eat.server.dao.TypeDao;
import com.what.to.eat.server.po.Type;
import com.what.to.eat.server.service.TypeService;
import org.springframework.stereotype.Service;

/**
 * Type对应service实现
 * 
 * @author auto 2020年11月09日 
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeDao,Type> implements TypeService {

}