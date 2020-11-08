package com.what.to.eat.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.what.to.eat.server.po.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Attachment对应Service
 *
 * @author auto 2020年11月07日
 */
public interface AttachmentService extends IService<Attachment> {

    Attachment getBySha1(String sha1);
}
