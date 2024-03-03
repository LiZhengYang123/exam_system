package com.yang.service.admin_service;

import com.yang.dto.ExamScoreDTO;
import com.yang.vo.Result;

public interface AdminOperateExamScoreService {
    Result getSpecialExamAllScore(Long examId);

    Result getSpecialExamSpecialStudentScore(Long examId, Long studentId);

    Result updateExamScore(ExamScoreDTO examScoreDTO);
}
