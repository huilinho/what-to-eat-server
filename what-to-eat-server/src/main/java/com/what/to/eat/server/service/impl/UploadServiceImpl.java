package com.what.to.eat.server.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import com.what.to.eat.server.config.AppConfig;
import com.what.to.eat.server.po.Attachment;
import com.what.to.eat.server.service.AttachmentService;
import com.what.to.eat.server.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.exception.ScodeRuntimeException;
import net.scode.commons.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author huilin
 * @version 1.0
 * @date 2020/11/7 21:21
 */
@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    AppConfig appConfig;

    @Autowired
    AttachmentService attachmentService;

    @Override
    public String upload(MultipartFile file, String basePath, boolean jpgCompress) {
        try{
            String sha1 = SecureUtil.sha1(file.getInputStream());
            Attachment attachment = attachmentService.getBySha1(sha1);
            //如果附件存在，则直接返回url
            if(attachment!=null){
                return attachment.getUrl();
            }
            String type = FileTypeUtil.getType(file.getInputStream());
            boolean isJPG = false;
            //如果文件格式是图片
            if(ImgUtil.IMAGE_TYPE_JPEG.equals(type)||ImgUtil.IMAGE_TYPE_JPG.equals(type)){
                    isJPG = true;
            }
            String filename = file.getOriginalFilename();
            String suffix = FileUtil.extName(filename);
            String filePrefix = UUID.randomUUID().toString();
            String newFileName = filePrefix+ "." + suffix;
            String thumbFileName = filePrefix+"_thumb."+suffix;
            String datePath;
            if(StringUtil.isEmpty(basePath)){
                datePath="";
            }
            else{
                datePath=basePath+"/";
            }
            datePath += new DateTime().toString("yyyy-MM");
            String filePath = datePath+"/"+newFileName;
            File outFile = FileUtil.file(appConfig.getUploadBasePath(), filePath);
            log.debug("outFile:{}", outFile.getAbsoluteFile());
            FileUtil.writeFromStream(file.getInputStream(), outFile);
            //生成一个缩略图
            if (ImgUtil.IMAGE_TYPE_JPEG.equals(type) || ImgUtil.IMAGE_TYPE_JPG.equals(type)
                    || ImgUtil.IMAGE_TYPE_BMP.equals(type) || ImgUtil.IMAGE_TYPE_GIF.equals(type)
                    || ImgUtil.IMAGE_TYPE_PNG.equals(type)) {
                String thumbFilePath = datePath + "/" + thumbFileName;
                File thumbOutFile = FileUtil.file(appConfig.getUploadBasePath(), thumbFilePath);
                FileUtil.writeFromStream(file.getInputStream(), thumbOutFile);
                ImgUtil.scale(thumbOutFile, thumbOutFile, 300, 300, null);
            }

            String fileUrl = appConfig.getUploadBaseUrl()+filePath; //生成访问地址

            attachment = new Attachment();
            attachment.setPath(outFile.getAbsolutePath());
            attachment.setSha1(sha1);
            attachment.setUrl(fileUrl);
            attachment.setCreateTime(DateUtil.date());
            return fileUrl;
        }catch (IOException e){
            throw new ScodeRuntimeException("文件保存失败！");
        }
    }

    @Override
    public File getByUrl(String url) {
        if(url==null){
            return null;
        }
        String path = url.replace(appConfig.getUploadBaseUrl(), appConfig.getUploadBasePath());
        File outFile = FileUtil.file(path);
        return outFile;
    }
}
