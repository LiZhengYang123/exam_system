package com.yang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//开启事务管理
@EnableTransactionManagement
@SpringBootApplication
public class ExamSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamSystemApplication.class, args);
        System.out.println("---------------------------------------------------------" +
                "--------------------------------------------------------------------" +
                "--------------------------------------------------------------------");
        System.err.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->_v_<- 启动成功 ->_v_<-");
    }

}
