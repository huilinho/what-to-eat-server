package com.what.to.eat.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.what.to.eat.server.dao.CollectionDao;
import com.what.to.eat.server.po.Collection;
import com.what.to.eat.server.service.CollectionService;
import org.springframework.stereotype.Service;

/**
 * Collection对应service实现
 * 
 * @author auto 2020年11月05日 
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionDao,Collection> implements CollectionService {

}