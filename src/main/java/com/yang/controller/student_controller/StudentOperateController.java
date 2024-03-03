package com.yang.controller.student_controller;


import com.yang.service.student_service.StudentOperateService;
import com.yang.utils.LocalStorageUtil;
import com.yang.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Slf4j
@Api("学生相关")
@RequestMapping("/student")
@RestController
public class StudentOperateController {

    @Autowired
    private StudentOperateService operateService;


    /**
     * 查询当前学生所经历的所有考试
     */
    @ApiOperation("所有考试")
    @GetMapping("/exam/list")
    public Result getExamList(){
        Map<String,Object>map= (Map<String, Object>) LocalStorageUtil.getStorage();
        log.info("查询id为{}学生{}所经历的所有考试",map.get("userId"),map.get("userName"));
        return operateService.getExamList();
    }

    @ApiOperation("考试成绩")
    @GetMapping("/examScore/{examId}")
    public Result getExamScore(@PathVariable Long examId){
        Map<String,Object>map= (Map<String, Object>) LocalStorageUtil.getStorage();
        log.info("查询id为{}的学生{}考试id为{}的成绩",map.get("userId"),map.get("userName"),examId);
        return operateService.getExamScore(examId);
    }

}
