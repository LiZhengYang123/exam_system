package com.yang.service.student_service;

import com.yang.vo.Result;

public interface StudentOperateService {
    Result getExamList();

    Result getExamScore(Long examId);
}
