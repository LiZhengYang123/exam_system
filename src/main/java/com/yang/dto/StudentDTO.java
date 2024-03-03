package com.yang.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {
    private Long id;
    private String name;        //学生姓名
    private Integer gender;     //学生性别
    private String number;      //学生学号

    public static final Integer GENDER_BOY=1;       //男
    public static final Integer GENDER_GIRL=2;      //女
}
