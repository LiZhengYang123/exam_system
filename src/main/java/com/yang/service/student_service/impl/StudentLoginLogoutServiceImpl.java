package com.yang.service.student_service.impl;

import com.yang.constant.Message;
import com.yang.dto.LoginDTO;
import com.yang.exception.BaseException;
import com.yang.mapper.UserMapper;
import com.yang.pojo.User;
import com.yang.service.student_service.StudentLoginLogoutService;
import com.yang.utils.JwtUtil;
import com.yang.utils.LocalStorageUtil;
import com.yang.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class StudentLoginLogoutServiceImpl implements StudentLoginLogoutService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    
    @Override
    public Result login(LoginDTO loginDTO) {

        //处理业务 ...
        User user = userMapper.studentLogin(loginDTO);

        //登录失败
        if (user==null){
            throw new BaseException(Message.LOGIN_FAILED);
        }

        //登陆成功
        //创建数据映射map
        Map<String,Object>claims=new HashMap<>();

        //封装用户信息 ...
        claims.put("userId",user.getId());
        claims.put("username",user.getUsername());
        claims.put("userName",user.getName());

        //创建JWT令牌
        String jwtToken=JwtUtil.createJWT(claims);
        
        //将JWT令牌存入redis缓存中
        ValueOperations operations = redisTemplate.opsForValue();
        operations.set(user.getId(),jwtToken,JwtUtil.ttlMillis,TimeUnit.MILLISECONDS);

        //将claims作为本次线程存储变量
        LocalStorageUtil.setStorage(claims);

        return Result.success(jwtToken);
    }

    @Override
    public Result logout() {
        //从本次线程存储中map
        Map<String,Object>map = (Map<String, Object>) LocalStorageUtil.getStorage();

        //将缓存中的令牌移除
        redisTemplate.opsForValue().getOperations().delete(Long.valueOf(map.get("userId").toString()));

        return Result.success();
    }
}
