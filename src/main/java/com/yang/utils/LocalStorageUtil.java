package com.yang.utils;

public class LocalStorageUtil {
    private static ThreadLocal threadLocal=new ThreadLocal();

    //获取本次储存的内容
    public static Object getStorage(){
        return threadLocal.get();
    }

    //设置本次线程储存
    public static void setStorage(Object value){
        threadLocal.set(value);
    }

    //移除储存
    public static void remove(){
        threadLocal.remove();
    }
}
