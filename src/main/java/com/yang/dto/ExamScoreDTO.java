package com.yang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamScoreDTO {
    private Long id;
    private Long studentId;
    private Long examId;
    private Float chineseScore;
    private Float mathScore;
    private Float englishScore;
    private Float totalScore;
}
