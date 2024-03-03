package com.yang.mapper;

import com.yang.pojo.Exam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamMapper {


    int deleteById(Long examId);

    Exam selectById(Long examId);

    List<Exam> selectList();

    int update(Exam exam);

    int insert(Exam exam);
}
