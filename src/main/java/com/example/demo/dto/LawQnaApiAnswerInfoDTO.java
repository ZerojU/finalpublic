package com.example.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LawQnaApiAnswerInfoDTO {
    private int rank;  //1번이 제일 높은 정확도를 가짐
    private String answer;  //결과
    private String source;  //소스
    private String clause;  //근거 법령(자세히)
    private double confidence;  //신뢰도
}
