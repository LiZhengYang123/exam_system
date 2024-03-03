package com.yang.controller.admin_controller;


import com.yang.dto.StudentDTO;
import com.yang.service.admin_service.AdminOperateStudentBaseInfoService;
import com.yang.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理端操作学生的基本信息(不包含考试成绩)
 */

@Slf4j
@Api(tags = "管理端操作学生基本信息")
@RestController
@RequestMapping("/admin/student")
public class AdminOperateStudentBaseInfoController {

    @Autowired
    private AdminOperateStudentBaseInfoService operateStudentBaseInfoService;

    /**
     * 新增学生
     */
    @ApiOperation("新增学生")
    @PostMapping
    public Result addStudent(@RequestBody StudentDTO studentDTO){
        log.info("新增学生:{}",studentDTO);
        return operateStudentBaseInfoService.addStudent(studentDTO);
    }

    /**
     * 根据学生id获取学生信息
     */
    @ApiOperation("获取学生")
    @GetMapping("/{studentId}")
    public Result getStudent(@PathVariable Long studentId){
        log.info("获取id为{}的学生信息",studentId);
        return operateStudentBaseInfoService.getStudent(studentId);
    }

    /**
     * 获取学生信息列表
     */
    @ApiOperation("获取学生信息列表")
    @GetMapping("/list")
    public Result getStudentList(){
        log.info("获取学生信息列表...");
        return operateStudentBaseInfoService.getStudentList();
    }

    /**
     * 根据id删除学生
     */
    @ApiOperation("删除学生")
   @DeleteMapping("/{studentId}")
   public  Result deleteStudent(@PathVariable Long studentId){
        log.info("删除id为{}的学生信息",studentId);
        return operateStudentBaseInfoService.deleteStudent(studentId);
   }


    /**
     * 修改学生信息
     */
    @ApiOperation("修改学生信息")
    @PutMapping
    public Result updateStudent(@RequestBody StudentDTO studentDTO){
        log.info("修改id为{}的学学生信息为：\n\t{}",studentDTO);
        return operateStudentBaseInfoService.updateStudent(studentDTO);
    }

}
