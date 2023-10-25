package com.example.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
@RequestMapping("/")
public class LawPageController {

    //2. restcontroller는 일반 getmapping 페이지를 리턴하지 않는다는 사실을 기억

    /*
     *   패턴
     *
     *   / 메인 페이지
     *   /legalqna   큐엔에이 검색 결과 페이지
     *   /precedent 판례 검색 결과 페이지
     *   /clause  법령 검색 결과 페이지
     *   /history  검색 결과 히스토리
     *
     *   /search/prec  판례 키워드 검색 폼
     *   /search/clau  법령 키워드 검색 폼
     *   /search/qna  법령 검색 결과 페이지
     *
     *  */

    @GetMapping()
    public String indexPageOpen() {
        log.info("main index page opened");
        return "index";
    }

    @GetMapping("legalqna")
    public void qnaSearchPageOpen() {
        //큐엔에이 기본 페이지를 오픈
        log.info("qna search page opened");

    }

    @GetMapping("precedent")
    public void precSearchPageOpen() {
        //판례 검색 기본 페이지를 오픈
        log.info("precedent search page opened");
    }

    @GetMapping("clause")
    public void clauSearchPageOpen() {
        //법령 검색 기본 페이지를 오픈
        log.info("clause search page opened");
    }

    @GetMapping("history")
    public void historyPageOpen() {
        // 정보 검색 결과 히스토리 페이지를 오픈
        log.info("law search history page opened");
    }




}
