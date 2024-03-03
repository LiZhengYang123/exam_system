package com.yang.service.admin_service.impl;

import com.yang.mapper.ExamMapper;
import com.yang.mapper.ExamScoreMapper;
import com.yang.pojo.Exam;
import com.yang.pojo.ExamScore;
import com.yang.service.student_service.StudentOperateService;
import com.yang.utils.LocalStorageUtil;
import com.yang.vo.ExamScoreVO;
import com.yang.vo.ExamVO;
import com.yang.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StudentOperateServiceImpl implements StudentOperateService {

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private ExamScoreMapper examScoreMapper;


    @Override
    public Result getExamList() {
        //拿到学生id
        Map<String,Object>map = (Map<String, Object>) LocalStorageUtil.getStorage();
        Long studentId=Long.valueOf(map.get("userId").toString());

        //拿到学生所有的考试成绩
        List<ExamScore>examScoreList=examScoreMapper.selectListByStudentId(studentId);

       //通过学生的考试id拿到具体考试信息
        List<ExamVO>studentExamVo=new ArrayList<>();
        for (ExamScore examScore : examScoreList) {
            Exam exam=examMapper.selectById(examScore.getExamId());
            studentExamVo.add(ExamVO.builder()
                            .id(exam.getId())
                            .name(exam.getName())
                            .time(exam.getTime())
                    .build());
        }

        return Result.success(studentExamVo);
    }

    @Override
    public Result getExamScore(Long examId) {
        //拿到学生id
        Map<String,Object>map= (Map<String, Object>) LocalStorageUtil.getStorage();
        Long studentId= Long.valueOf( map.get("userId").toString());

        //根据学生id和考试id获取考试成绩
        ExamScore examScore=examScoreMapper.selectByStudentIdAndExamId(studentId,examId);
        ExamScoreVO examScoreVO = ExamScoreVO.builder()
                .id(examScore.getId())
                .studentId(examScore.getStudentId())
                .examId(examScore.getExamId())
                .chineseScore(examScore.getChineseScore())
                .mathScore(examScore.getMathScore())
                .englishScore(examScore.getEnglishScore())
                .totalScore(examScore.getTotalScore())
                .build();
        return Result.success(examScoreVO);
    }
}
