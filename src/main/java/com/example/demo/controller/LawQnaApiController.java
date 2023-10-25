package com.example.demo.controller;

import com.example.demo.dto.LawQnaApiAnswerInfoDTO;
import com.example.demo.errorhandler.CustomErrorCode;
import com.example.demo.service.TextKeywordExtractService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.LawQnaApiService;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@Controller
@Log4j2
@Validated
@RequestMapping("/search")
public class LawQnaApiController {

    @Autowired
    private LawQnaApiService lawQnaApiService;
    @Autowired
    private TextKeywordExtractService keywordExtractService;

    @PostMapping("/legalqna")
    public void getLegalqna(
            @RequestParam("qna-query") @NotBlank(message = "잘못된 입력입니다.") @Size(max = 200) String qnaQuery,
            Model model) {
        log.info("law api qna page opened");

        // 질문 객체를 넘김
        model.addAttribute("question", qnaQuery);

        // json 형태의 결과를 가져옴
        String answerJson = lawQnaApiService.getLegalQA(qnaQuery);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(answerJson);
            int resultCode = rootNode.get("result").asInt();
            log.info("QnA API result outcomes; resultCode : " + resultCode);

            //jackson을 통해 json을 list 형태로 저장
            JsonNode answerInfoArray = rootNode
                    .path("return_object")
                    .path("LegalInfo")
                    .path("AnswerInfo");

            List<LawQnaApiAnswerInfoDTO> answerInfoList = objectMapper.convertValue(answerInfoArray, new TypeReference<>() {});


            if (resultCode == 0) { // 코드가 0(정상)일 때
                if (!answerInfoList.isEmpty()) { // 결과가 존재
                    //리스트에서 저신뢰도 결과를 제거
                    lawQnaApiService.lowConfidenceRemove(answerInfoList);

                    // 가장 빈도수가 높은 단어를 키워드로 하여 가장 많은 3가지를 가져와 출력
                    List<String> keywordTopThree = keywordExtractService.getKeyword(answerInfoList.get(0).getClause());
                    model.addAttribute("topThree", keywordTopThree);

                    log.info("law qna api answer list created");

                    //만들어진 리스트를 보냄
                    model.addAttribute("answerInfoList", answerInfoList);
                    model.addAttribute("isListExists", true);
                } else {
                    log.info("answer list is Empty...");
                    model.addAttribute("isListExists", false);
                }
            } else { //resultCode 가 -1일 경우
                throw new ResponseStatusException(
                        CustomErrorCode.API_FORBIDDEN_ERROR.getHttpStatus(),
                        CustomErrorCode.API_FORBIDDEN_ERROR.getErrorMessage()
                );
            }

        } catch (JsonProcessingException e) {
            log.error("Law QnA Api Json Processing Exception occurred");
            e.printStackTrace();
        }
    }
}
    //헤멘 것 1: 계속해서 404 에러가 남 -> 해결(확장자가 mustache가 아니라서 인식하지 못했음)










