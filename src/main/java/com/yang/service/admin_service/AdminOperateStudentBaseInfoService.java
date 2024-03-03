package com.yang.service.admin_service;

import com.yang.dto.StudentDTO;
import com.yang.vo.Result;

public interface AdminOperateStudentBaseInfoService {


    /**
     * 新增学生
     */
    Result addStudent(StudentDTO studentDTO);

    /**
     * 获取学生信息
     */
    Result getStudent(Long studentId);

    Result updateStudent(StudentDTO studentDTO);

    Result deleteStudent(Long studentId);

    Result getStudentList();
}
