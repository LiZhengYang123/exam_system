package com.yang.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Result {
    private Integer code;
    private String message;
    private Object data;

    //响应码，1成功，0失败
    private static final Integer SUCCESS=1;
    private static final Integer ERROR=0;

    //操作成功，不用提示
    //操作失败，提示失败
    private static final String MESSAGE_SUCCESS="";
    private static final String MESSAGE_ERROR="操作失败";

    //返回操作成功的结果
    public static Result success(){
        return new Result(SUCCESS,MESSAGE_SUCCESS,null);
    }

    public static Result success(Object data){
        return new Result(SUCCESS,MESSAGE_SUCCESS,data);
    }

    //返回操作失败的结果
    public static Result error(){
        return new Result(ERROR,MESSAGE_ERROR,null);
    }

    public static Result error(String message){
        return new Result(ERROR,message,null);
    }

}
