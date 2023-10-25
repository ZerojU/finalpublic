package com.example.demo.service;

import com.example.demo.apiproperties.ApiConfig;
import com.example.demo.dto.LawQnaApiAnswerInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class LawQnaApiService {

    //webclient를 통해 api 원주소를 생성, post request을 통해 법률 Q&A api response를 받는 서비스입니다.
    //api 키의 경우 application.properties에서 가져옵니다.
    //String 형식의 동기 형식으로 response를 받고 있습니다.

    private final WebClient webClient;
    private final ApiConfig apiConfig;

    @Autowired
    public LawQnaApiService(ApiConfig apiConfig) {
        this.webClient = WebClient.builder()
                .baseUrl("http://aiopen.etri.re.kr:8000")
                .build();
        this.apiConfig = apiConfig;
    }

    public String getLegalQA(String question) {
        //application properties에 저장한 키를 가져옴
        String lawQnaApiKey = apiConfig.getLawApiQnaKey();

        // 요청 데이터 설정
        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();
        argument.put("question", question);
        request.put("argument", argument);

        //API 호출 및 응답 데이터 처리
        return webClient.post()
            .uri("/LegalQA")
            .header("Authorization", lawQnaApiKey)
            .body(BodyInserters.fromValue(request))
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(String.class)
                                      .map(body -> new ResponseStatusException(
                                              clientResponse.statusCode(),
                                              "CLIENT ERROR")))
            .onStatus(HttpStatusCode::is5xxServerError,
                    clientResponse -> clientResponse.bodyToMono(String.class)
                                      .map(body -> new ResponseStatusException(
                                              clientResponse.statusCode(),
                                              "API 서버에 문제가 발생하였습니다.")))
            .bodyToMono(String.class)
            .block();  //동기형
    }

    //신뢰율이 너무 낮아서 쓸모없는 결과를 배제
    public void lowConfidenceRemove(List<LawQnaApiAnswerInfoDTO> answerList) {
        Iterator<LawQnaApiAnswerInfoDTO> iterator = answerList.iterator();

        //반복 객체를 생성하여 다음이 있다면 계속해서 반복(안전한 삭제)
        while (iterator.hasNext()) {
            //신뢰율이 0.01 이하인 것은 리스트에서 제거
            if (iterator.next().getConfidence() < 0.01) {
                iterator.remove();
            }
        }
    }

    public String collectClause(List<LawQnaApiAnswerInfoDTO> answerList) {
        System.out.println("answerlist : " + answerList);
        if (answerList != null) {
            String clause = null;
            for (LawQnaApiAnswerInfoDTO answer : answerList) {
                clause += answer.getClause();
            }
            System.out.println(clause);
            return clause;
        }
        return null;
    }


    /* .onStatus(HttpStatus.FORBIDDEN::equals, clientResponse -> {
                if (clientResponse.statusCode() == HttpStatus.FORBIDDEN) {
                    Mono.error(
                            new BadWebClientRequestException(
                                    clientResponse.statusCode().value(),
                                    String.format("4xx 외부 요청 오류. statusCode: %s, response: %s, header: %s",
                                            clientResponse.statusCode(),
                                            clientResponse.bodyToMono(String.class),
                                            clientResponse.headers().asHttpHeaders())
                            ));
                }
                return null;

            })
            //input(query)비어 있으면 500에러를 주는 듯 함
            .onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals, clientResponse -> {
                return Mono.error(
                            new BadWebClientRequestException(
                                    clientResponse.statusCode().value(),
                                    String.format("5xx 외부 서버 오류. statusCode: %s, response: %s, header: %s",
                                            clientResponse.statusCode(),
                                            clientResponse.bodyToMono(String.class),
                                            clientResponse.headers().asHttpHeaders())
                            )
                    );

            })


       */


}
