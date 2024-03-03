package com.yang.service.admin_service.impl;

import com.yang.dto.LoginDTO;
import com.yang.exception.BaseException;
import com.yang.mapper.UserMapper;
import com.yang.pojo.User;
import com.yang.service.admin_service.AdminLoginLogoutService;
import com.yang.utils.JwtUtil;
import com.yang.utils.LocalStorageUtil;
import com.yang.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AdminLoginLogoutServiceImpl implements AdminLoginLogoutService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result login(LoginDTO loginDTO) {
        //账号密码与身份是否匹配
        User user = userMapper.adminLogin(loginDTO);

        if (user==null){
            throw  new BaseException("登录信息有误");
        }

        //登录成功，准备下发JWT令牌
        //准备令牌body
        Map<String,Object>map=new HashMap<>();
        map.put("userId",user.getId());
        map.put("userName",user.getName());
        map.put("username",user.getUsername());

        //生成令牌
        String jwtToken = JwtUtil.createJWT(map);

        //将令牌存入redis
        redisTemplate.opsForValue().set(user.getId(),jwtToken,JwtUtil.ttlMillis, TimeUnit.MILLISECONDS);

        //将map作为线程变量
        LocalStorageUtil.setStorage(map);

        return Result.success(jwtToken);
    }

    @Override
    public Result logout() {
        //获取线程变量map
        Map<String,Object>map= (Map<String, Object>) LocalStorageUtil.getStorage();

        //获取用户id
        Long userId = Long.valueOf(map.get("userId").toString());

        //将redis缓存中的令牌移除
        redisTemplate.opsForValue().getOperations().delete(userId);

        return Result.success();
    }
}
