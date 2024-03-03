package com.yang.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentVO {
    private Long id;
    private Long examId;
    private Long examScoreId;
    private String name;
    private Float chineseScore;
    private Float mathScore;
    private Float englishScore;
    private Float totalScore;
}
