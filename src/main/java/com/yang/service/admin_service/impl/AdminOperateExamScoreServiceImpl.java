package com.yang.service.admin_service.impl;

import com.yang.dto.ExamScoreDTO;
import com.yang.mapper.ExamScoreMapper;
import com.yang.mapper.UserMapper;
import com.yang.pojo.ExamScore;
import com.yang.pojo.User;
import com.yang.service.admin_service.AdminOperateExamScoreService;
import com.yang.vo.ExamScoreVO;
import com.yang.vo.ExamVO;
import com.yang.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class AdminOperateExamScoreServiceImpl implements AdminOperateExamScoreService {


    @Autowired
    private ExamScoreMapper examScoreMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result getSpecialExamAllScore(Long examId) {
        List<ExamScore>examScoreList=examScoreMapper.selectListByExamId(examId);
        List<ExamScoreVO>examScoreVOList=new ArrayList<>();
        for (ExamScore examScore : examScoreList) {
            ExamScoreVO examScoreVO=new ExamScoreVO();
            BeanUtils.copyProperties(examScore,examScoreVO);
            examScoreVOList.add(examScoreVO);
        }
        log.info("成功获取到{}条学生成绩",examScoreVOList.size());
        return Result.success(examScoreVOList);
    }

    @Override
    public Result getSpecialExamSpecialStudentScore(Long examId, Long studentId) {
        ExamScore examScore=examScoreMapper.selectByStudentIdAndExamId(studentId,examId);
        ExamScoreVO examScoreVO=new ExamScoreVO();
        BeanUtils.copyProperties(examScore,examScoreVO);
        User user = userMapper.selectById(studentId);
        examScoreVO.setStudentName(user.getName());
        return Result.success(examScoreVO);
    }

    @Override
    public Result updateExamScore(ExamScoreDTO examScoreDTO) {
        examScoreDTO.setTotalScore(examScoreDTO.getChineseScore()+ examScoreDTO.getMathScore()+ examScoreDTO.getEnglishScore());
        int updateCount=examScoreMapper.update(examScoreDTO);
        log.info("成功更新{}条学生成绩",updateCount);
        return Result.success();
    }
}
