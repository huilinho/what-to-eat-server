package com.what.to.eat.server.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author huilin
 * @version 1.0
 * @date 2020/11/7 21:21
 */
public interface UploadService {
    String upload(MultipartFile file, String basePath, boolean jpgCompress);
    File getByUrl(String url);
}
