package com.yang.service.admin_service;

import com.yang.dto.ExamDTO;
import com.yang.vo.Result;

public interface AdminOperateExamBaseInfoService {
    Result deleteExam(Long examId);

    Result getExam(Long examId);

    Result getExamList();

    Result addExam(ExamDTO examDTO);

    Result updateExam(ExamDTO examDTO);
}
