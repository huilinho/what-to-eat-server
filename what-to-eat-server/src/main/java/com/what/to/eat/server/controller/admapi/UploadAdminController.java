package com.what.to.eat.server.controller.admapi;

import com.what.to.eat.server.service.UploadService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

/**
 * @author huilin
 * @version 1.0
 * @date 2020/11/7 22:01
 */
@Slf4j
@Api(tags = {"后台上传接口"})
@RestController
@RequestMapping(value = "/admapi/upload")
public class UploadAdminController {
    @Autowired
    private UploadService uploadService;

    @PostMapping
    public R uploadFile(@RequestParam @NotNull(message = "上传文件不能为空") MultipartFile file) {
        String fileUrl = uploadService.upload(file, null, true);
        HashMap<String, Object> map = new HashMap<>();
        map.put("url", fileUrl);
        return R.data(map);
    }
}
