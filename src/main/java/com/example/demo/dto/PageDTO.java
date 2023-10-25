package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Data
public class PageDTO {

    /*                current
    *     first     4     5     6      last
    *           curr-1       curr+1
    *            prev         next
    *
    * */

    private int currentPage;  //현재 선택된 페이지  (ex: 13)
    private Integer nextPage;  //현재 선택된 페이지의 다음 페이지
    private Integer previousPage;  //현재 페이지의 직전 페이지
    private Integer lastPage;   //total 값을 통해 계산한 맨 마지막 페이지

    public PageDTO(int total, int currentPage) {
        this.nextPage = currentPage + 1;
        this.previousPage = currentPage - 1;
        this.lastPage = (int) (Math.ceil(total / 5.0));
        this.currentPage = Math.min(currentPage, this.lastPage);
    }

}


