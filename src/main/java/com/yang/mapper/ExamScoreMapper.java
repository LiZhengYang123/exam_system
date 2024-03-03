package com.yang.mapper;

import com.yang.dto.ExamScoreDTO;
import com.yang.pojo.ExamScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExamScoreMapper {

    int insertList(List<ExamScore> examScoreList);

    int deleteByExamId(Long examId);

    int deleteByStudentId(Long studentId);

    List<ExamScore> selectListByStudentId(Long studentId);

    ExamScore selectByStudentIdAndExamId(@Param("studentId") Long studentId, @Param("examId") Long examId);

    List<ExamScore> selectListByExamId(Long examId);

    int update(ExamScoreDTO examScoreDTO);

}

