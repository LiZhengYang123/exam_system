package com.yang.controller.student_controller;

import com.yang.dto.LoginDTO;
import com.yang.service.student_service.StudentLoginLogoutService;
import com.yang.utils.LocalStorageUtil;
import com.yang.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Api(tags = "登录退出")
@RequestMapping("/student")
@RestController
public class StudentLoginLogoutController {

    @Autowired
    private StudentLoginLogoutService loginLogoutService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO){
        log.info("学生端登录：{}",loginDTO);
        return loginLogoutService.login(loginDTO);
    }

    @ApiOperation(value = "退出")
    @GetMapping("/logout")
    public Result logout(){
        Map<String,Object>map = (Map<String, Object>) LocalStorageUtil.getStorage();
        Long userId= Long.valueOf(map.get("userId").toString());
        String userName = (String) map.get("userName");
        log.info("学生端退出:学生id={}\t学生姓名: {}",userId,userName);
        return loginLogoutService.logout();
    }

}
