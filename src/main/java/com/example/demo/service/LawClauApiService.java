package com.example.demo.service;

import com.example.demo.apiproperties.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LawClauApiService {

    private final WebClient webClient;
    private final ApiConfig apiConfig;

    @Autowired
    public LawClauApiService(ApiConfig apiConfig) {
        this.webClient = WebClient.builder()
                .baseUrl("http://www.law.go.kr/DRF/lawSearch.do?")
                .build();
        this.apiConfig = apiConfig;
    }

    //법령 목록을 리턴하는 함수
    public String getClausesFromLaws(String query, int searchType, int page) {
        //application properties에 저장한 키를 가져옴
        String lawClauApiKey = apiConfig.getLawApiPrecClauKey();

        // API 호출 및 응답 데이터 처리
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("OC", lawClauApiKey)
                        .queryParam("target", "law")
                        .queryParam("type", "XML")
                        .queryParam("search", searchType)  //1 = 법령명
                        .queryParam("query", query)
                        .queryParam("display", 5)
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();  //동기형
    }

    //(230915)서비스로 옮겨서 주석 처리함
//    public PageDTO setClauPaging(int total, int page) {
//        PageDTO pageDTO = new PageDTO(total, page);
//
//        // 페이징 예외 처리
//        // 첫 페이지일 경우 이전 페이지 없음. 마지막 페이지일 경우 다음 페이지 없음
//        if (page == 1) pageDTO.setPreviousPage(null);
//        if (page == pageDTO.getLastPage()) pageDTO.setNextPage(null);
//        if (total == 0) {
//            pageDTO.setNextPage(null);
//            pageDTO.setLastPage(null);
//        }
//        return pageDTO;
//    }
}
