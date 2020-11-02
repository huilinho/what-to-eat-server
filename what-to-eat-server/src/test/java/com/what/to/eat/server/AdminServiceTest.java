package com.what.to.eat.server;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.what.to.eat.server.enums.Gender;
import com.what.to.eat.server.po.Admin;
import com.what.to.eat.server.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    @Test
    public void addUser() {
        Admin admin = new Admin();
        admin.setMobile("13381176580");
        admin.setPassword(SecureUtil.md5("123456"));
        admin.setUsername("管理员");
        admin.setNickname("admin");
        admin.setAvatar("");
        admin.setCreateTime(DateUtil.date());
        adminService.save(admin);

    }
}
