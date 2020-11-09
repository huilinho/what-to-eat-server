package com.what.to.eat.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.what.to.eat.server.dao.WindowDao;
import com.what.to.eat.server.po.Window;
import com.what.to.eat.server.service.WindowService;
import org.springframework.stereotype.Service;

/**
 * Window对应service实现
 * 
 * @author auto 2020年11月09日 
 */
@Service
public class WindowServiceImpl extends ServiceImpl<WindowDao,Window> implements WindowService {

}