package com.yang.interceptor;

import com.yang.exception.BaseException;
import com.yang.utils.JwtUtil;
import com.yang.utils.LocalStorageUtil;
import io.jsonwebtoken.Claims;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        log.info("拦截到请求:{}",request.getRequestURI());
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        String token=request.getHeader(JwtUtil.tokenName);

        if (StringUtils.isEmpty(token)){
            throw new BaseException("未携带JWT令牌");
        }
        Map<String,Object>map = JwtUtil.parseJWT(token);
        Long userId=Long.valueOf(map.get("userId").toString());

        ValueOperations operations = redisTemplate.opsForValue();
        // 从redis中判断用户是否已经退出
        if (operations.get(userId)==null){
            throw new BaseException("用户已退出");
        }

        //将map储存到本次线程中
        LocalStorageUtil.setStorage(map);

        return true;
    }
}
