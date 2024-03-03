package com.yang.exception;

public class BaseException extends RuntimeException{

    public BaseException() {
    }

    public BaseException(String msg) {
        super(msg);
    }

}
