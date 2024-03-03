package com.yang.handler;

import com.yang.exception.BaseException;
import com.yang.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public Result exceptionHandler(BaseException e){
        //打印异常信息
        log.error("全局异常处理器发现异常：{}",e.getMessage());
        return Result.error(e.getMessage());
    }

}
