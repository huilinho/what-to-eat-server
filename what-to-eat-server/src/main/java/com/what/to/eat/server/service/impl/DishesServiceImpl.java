package com.what.to.eat.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.what.to.eat.server.dao.DishesDao;
import com.what.to.eat.server.po.Dishes;
import com.what.to.eat.server.service.DishesService;
import org.springframework.stereotype.Service;

/**
 * Dishes对应service实现
 * 
 * @author auto 2020年11月05日 
 */
@Service
public class DishesServiceImpl extends ServiceImpl<DishesDao,Dishes> implements DishesService {

}