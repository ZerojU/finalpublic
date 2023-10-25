package com.example.demo.service;


import com.example.demo.dto.PageDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ApiPagingService {

    public PageDTO setPaging(int total, int page) {
        PageDTO pageDTO = new PageDTO(total, page);

        // 페이징 예외 처리
        // 첫 페이지일 경우 이전 페이지 없음. 마지막 페이지일 경우 다음 페이지 없음
        // 검색 결과가 없을 때 다음 및 마지막 페이지 없음
        if (page == 1) pageDTO.setPreviousPage(null);
        if (page == pageDTO.getLastPage()) pageDTO.setNextPage(null);
        if (total == 0) {
            pageDTO.setNextPage(null);
            pageDTO.setLastPage(null);
        }

        return pageDTO;
    }


}
