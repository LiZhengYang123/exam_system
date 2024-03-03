package com.yang.controller.admin_controller;

import com.yang.dto.ExamDTO;
import com.yang.service.admin_service.AdminOperateExamBaseInfoService;
import com.yang.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api("管理端操作考试基本信息")
@RestController
@RequestMapping("/admin/exam")
public class AdminOperateExamBaseInfoController {


    @Autowired
    private AdminOperateExamBaseInfoService operateExamBaseInfoService;

    /**
     * 新增考试
     */
    @ApiOperation("新增考试")
    @PostMapping
    public Result addExam(@RequestBody ExamDTO examDTO){

        log.info("新增考试：\n{}",examDTO);
        return operateExamBaseInfoService.addExam(examDTO);
    }


    /**
     * 修改考试信息
     */
    @ApiOperation("修改考试")
    @PutMapping
    public Result updateExam(@RequestBody ExamDTO examDTO){

        //TODO 修改考试...
        log.info("修改id为{}的考试信息为:\n{}",examDTO.getId(),examDTO);
        return operateExamBaseInfoService.updateExam(examDTO);
    }


    /**
     * 删除考试信息
     */
    @ApiOperation("删除考试")
    @DeleteMapping("/{examId}")
    public Result deleteExam(@PathVariable Long examId){

        // 删除考试...
        log.info("删除id为{}的考试",examId);
        return operateExamBaseInfoService.deleteExam(examId);
    }

    /**
     * 获取考试信息
     */
    @ApiOperation("查询考试")
    @GetMapping("/{examId}")
    public Result getExam(@PathVariable Long examId){

        //TODO 根据id查询考试...
        log.info("查询id为{}的考试...",examId);
        return operateExamBaseInfoService.getExam(examId);
    }


    /**
     * 获取考试信息列表
     */
    @ApiOperation("查询考试列表")
    @GetMapping("/list")
    public Result getExamList(){

        //TODO 查询考试列表...
        log.info("获取考试信息列表");
        return operateExamBaseInfoService.getExamList();
    }



}
