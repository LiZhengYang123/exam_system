package com.yang.service.admin_service.impl;

import com.yang.dto.StudentDTO;
import com.yang.exception.BaseException;
import com.yang.mapper.ExamScoreMapper;
import com.yang.mapper.UserMapper;
import com.yang.pojo.User;
import com.yang.service.admin_service.AdminOperateStudentBaseInfoService;
import com.yang.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AdminOperateStudentBaseInfoServiceImpl implements AdminOperateStudentBaseInfoService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ExamScoreMapper examScoreMapper;


    @Override
    public Result addStudent(StudentDTO studentDTO) {
        User user=new User();
        BeanUtils.copyProperties(studentDTO,user);
        user.setUsername(studentDTO.getNumber());
        user.setIsAdmin(false);
        int dataCount = userMapper.insert(user);
        log.info("成功添加一个学生:\n{}",user);
        return Result.success();
    }

    @Override
    public Result getStudent(Long studentId) {
        User user = userMapper.selectById(studentId);
        if (user==null){
            throw new BaseException("id为"+studentId+"的用户不存在");
        }
        StudentDTO studentDTO= StudentDTO.builder()
                .id(user.getId())
                .gender(user.getGender())
                .number(user.getUsername())
                .name(user.getName())
                .build();
        return Result.success(studentDTO);
    }

    @Override
    public Result updateStudent(StudentDTO studentDTO) {
        User user=new User();
        BeanUtils.copyProperties(studentDTO,user);
        user.setUsername(studentDTO.getNumber());
        user.setIsAdmin(false);
        int updateCount = userMapper.update(user);
        log.info("成功更新"+updateCount+"条学生信息");
        return Result.success();
    }

    @Transactional
    @Override
    public Result deleteStudent(Long studentId) {

        //删除学生的所有考试成绩
        int deleteExamScoreCount=examScoreMapper.deleteByStudentId(studentId);
        log.info("成功删除学生{}的{}条考试成绩",deleteExamScoreCount);

        //删除学生
        int deleteCount=userMapper.deleteById(studentId);
        log.info("成功删除"+deleteCount+"条学生信息");
        return Result.success();
    }

    @Override
    public Result getStudentList() {
        List<User>userList=userMapper.selectList();
        List<StudentDTO>studentDTOS=new ArrayList<>();
        for (User user : userList) {
            if (!user.getIsAdmin()){
                StudentDTO studentDTO = StudentDTO.builder()
                        .gender(user.getGender())
                        .id(user.getId())
                        .name(user.getName())
                        .number(user.getUsername())
                        .build();
                studentDTOS.add(studentDTO);
            }
        }
        return Result.success(studentDTOS);
    }
}
