package com.what.to.eat.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.what.to.eat.server.dao.AttachmentDao;
import com.what.to.eat.server.po.Attachment;
import com.what.to.eat.server.service.AttachmentService;
import org.springframework.stereotype.Service;

/**
 * Attachment对应service实现
 *
 * @author auto 2020年11月07日
 */
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentDao,Attachment> implements AttachmentService {

    @Override
    public Attachment getBySha1(String sha1) {
        QueryWrapper<Attachment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sha1",sha1);
        Attachment attachment = getOne(queryWrapper);
        return attachment;
    }
}
