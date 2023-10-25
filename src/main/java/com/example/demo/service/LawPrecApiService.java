package com.example.demo.service;

import com.example.demo.apiproperties.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LawPrecApiService {

    // webclient를 통해 api 원주소를 생성, get request을 통해 판례 목록 및 본문 api response를 받는 서비스입니다.
    // api 키의 경우 application.properties에서 가져옵니다.
    // String 형식의 동기 형식으로 response를 받고 있습니다.

    private final WebClient webClient;
    private final ApiConfig apiConfig;

    @Autowired
    public LawPrecApiService(ApiConfig apiConfig) {
        this.webClient = WebClient.builder()
                .baseUrl("http://www.law.go.kr/DRF/lawSearch.do?")
                .build();
        this.apiConfig = apiConfig;
    }

    //판례 제목을 리턴하는 함수
    public String getPrecedentsFromCases(String query, int precType, int page) {
        //application properties에 저장한 키를 가져옴
        String lawPrecApiKey = apiConfig.getLawApiPrecClauKey();

        // API 호출 및 응답 데이터 처리
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("OC", lawPrecApiKey)
                        .queryParam("target", "prec")
                        .queryParam("type", "XML")
                        .queryParam("search", precType)
                        .queryParam("query", query)
                        .queryParam("display", 5)
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();  //동기형
    }

    //(230915)서비스로 옮겨서 주석 처리함
//    public PageDTO setPrecPaging(int total, int page) {
//        PageDTO pageDTO = new PageDTO(total, page);
//
//        // 페이징 예외 처리
//        // 첫 페이지일 경우 이전 페이지 없음. 마지막 페이지일 경우 다음 페이지 없음
//        if (page == 1) pageDTO.setPreviousPage(null);
//        if (page == pageDTO.getLastPage()) pageDTO.setNextPage(null);
//
//        return pageDTO;
//    }


//    //(230915)판례 본문을 리턴하는 함수였는데 type을 보내고 받기로 하면서 삼항연산을 지우고 함수도 필요없어져서 주석 처리함
//    public String getPrecedentTextsFromCases(String query, int precType) {
//        //application properties에 저장한 키를 가져옴
//        String lawPrecApiKey = apiConfig.getLawPrecApiKey();
//
//        // API 호출 및 응답 데이터 처리
//        return webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .queryParam("OC", lawPrecApiKey)
//                        .queryParam("target", "prec")
//                        .queryParam("type", "XML")
//                        .queryParam("search", precType)  //2 = 판례 본문
//                        .queryParam("query", query)
//                        .build())
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();  //동기형
//    }


}
