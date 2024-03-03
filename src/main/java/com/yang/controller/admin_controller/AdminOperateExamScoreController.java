package com.yang.controller.admin_controller;


import com.yang.dto.ExamScoreDTO;
import com.yang.service.admin_service.AdminOperateExamScoreService;
import com.yang.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "学生成绩")
@RestController
@RequestMapping("/admin/examScore")
public class AdminOperateExamScoreController {

    @Autowired
    private AdminOperateExamScoreService operateExamScoreService;

    @ApiOperation("获取一次考试所有成绩")
    @GetMapping("/{examId}")
    public Result getSpecialExamAllScore(@PathVariable Long examId){
        log.info("管理端获取id为{}的考试的所有学生成绩",examId);
        return operateExamScoreService.getSpecialExamAllScore(examId);
    }

    @ApiOperation("获取一次考试指定学生的详细考试信息")
    @GetMapping("/{examId}/{studentId}")
    public Result getSpecialExamSpecialStudentScore(
            @PathVariable Long examId,@PathVariable Long studentId){
        log.info("管理端获取id为{}的考试的id为{}的学生的成绩",examId,studentId);
        return operateExamScoreService.getSpecialExamSpecialStudentScore(examId,studentId);
    }

    @ApiOperation("更新学生成绩")
    @PutMapping
    public Result updateExamScore(@RequestBody ExamScoreDTO examScoreDTO){
        log.info("管理端更新id为{}的学生的id为{}的考试的成绩",examScoreDTO.getStudentId(),examScoreDTO.getExamId());
        return operateExamScoreService.updateExamScore(examScoreDTO);
    }

}
