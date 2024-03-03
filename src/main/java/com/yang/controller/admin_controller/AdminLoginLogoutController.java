package com.yang.controller.admin_controller;


import com.yang.dto.LoginDTO;
import com.yang.service.admin_service.AdminLoginLogoutService;
import com.yang.utils.LocalStorageUtil;
import com.yang.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 教师端登录退出
 */
@Slf4j
@Api(tags = "登录退出")
@RestController
@RequestMapping("/admin")
public class AdminLoginLogoutController {

    @Autowired
    AdminLoginLogoutService adminLoginLogoutService;


    @ApiOperation(value = "登录")
    @PostMapping("/login")
    Result login(@RequestBody LoginDTO loginDTO){
        log.info("管理端：{} 登录...",loginDTO.getUsername());
        return adminLoginLogoutService.login(loginDTO);
    }

    @ApiOperation(value = "退出")
    @GetMapping("/logout")
    Result logout(){
        Map<String,Object>map= (Map<String, Object>) LocalStorageUtil.getStorage();
        log.info("管理端：id为{}的用户'{}'正在退出登录...",map.get("userId"),map.get("userName"));
        return adminLoginLogoutService.logout();
    }

}
