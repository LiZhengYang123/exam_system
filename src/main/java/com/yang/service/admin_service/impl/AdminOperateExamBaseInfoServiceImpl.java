package com.yang.service.admin_service.impl;

import com.yang.dto.ExamDTO;
import com.yang.exception.BaseException;
import com.yang.mapper.ExamMapper;
import com.yang.mapper.ExamScoreMapper;
import com.yang.mapper.UserMapper;
import com.yang.pojo.Exam;
import com.yang.pojo.ExamScore;
import com.yang.pojo.User;
import com.yang.service.admin_service.AdminOperateExamBaseInfoService;
import com.yang.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdminOperateExamBaseInfoServiceImpl implements AdminOperateExamBaseInfoService {


    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private ExamScoreMapper examScoreMapper;

    @Autowired
    private UserMapper userMapper;



    @Transactional
    @Override
    public Result deleteExam(Long examId) {

        //删除所有当前考试的学生成绩
        int deleteExamScoreCount=examScoreMapper.deleteByExamId(examId);
        log.info("成功删除{}条学生考试{}的成绩",deleteExamScoreCount,examId);

        //删除考试基本信息
        int deleteCount=examMapper.deleteById(examId);
        log.info("成功删除{}条考试基本信息",deleteCount);
        return Result.success();
    }

    @Override
    public Result getExam(Long examId) {
        Exam exam =examMapper.selectById(examId);
        if (exam==null){
            throw new BaseException("id为"+examId+"的考试不存在");
        }
        ExamDTO examDTO=new ExamDTO();
        BeanUtils.copyProperties(exam,examDTO);
        return Result.success(examDTO);
    }

    @Override
    public Result getExamList() {
        List<Exam>examList=examMapper.selectList();
        List<ExamDTO>examDTOList=new ArrayList<>();
        for (Exam exam : examList) {
            ExamDTO examDTO=new ExamDTO();
            BeanUtils.copyProperties(exam,examDTO);
            examDTOList.add(examDTO);
        }
        return Result.success(examDTOList);
    }


    @Transactional
    @Override
    public Result addExam(ExamDTO examDTO) {

        //1.添加考试基本信息,添加完后顺便将考试id写入examDTO
        Exam exam=Exam.builder()
                .name(examDTO.getName())
                .time(examDTO.getTime())
                .build();
        int insertExamCount=examMapper.insert(exam);

        log.info("成功添加{}条考试信息",insertExamCount);

        //2.对于所有学生，创建这些学生该次的考试成绩
        //2.1查询出所有的用户
        List<User> userList = userMapper.selectList();

        //2.2筛选出所有学生
        List<User> studentList = userList.stream().filter((u)->!u.getIsAdmin()).collect(Collectors.toList());

        //2.3将考试成绩信息封装到一个集合里面
        List<ExamScore>examScoreList=new ArrayList<>();
        for (User student : studentList) {
            ExamScore examScore=ExamScore.builder()
                    .examId(exam.getId())
                    .studentId(student.getId())
                    .build();
            examScoreList.add(examScore);
        }

        //2.4写入考试成绩表
        int insertExamScoreCount=examScoreMapper.insertList(examScoreList);

        log.info("成功添加{}条考试成绩信息",insertExamScoreCount);

        return Result.success();
    }

    @Override
    public Result updateExam(ExamDTO examDTO) {

        Exam exam=Exam.builder()
                .id(examDTO.getId())
                .name(examDTO.getName())
                .time(examDTO.getTime())
                .build();

        //修改考试的基本信息
        int updateCount=examMapper.update(exam);
        log.info("成功更新{}条考试基本信息",updateCount);
        return Result.success();
    }
}
