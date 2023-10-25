package com.example.demo.controller;

import com.example.demo.service.LawPrecApiService;
import com.example.demo.service.LawQnaApiService;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import org.junit.jupiter.api.Test;

import java.util.List;


public class LawPrecApiControllerTest {

    private LawPrecApiService lawPrecApiService;
    private LawQnaApiService lawQnaApiService;

    //@Test
    //public void

    Komoran komoran = new Komoran(DEFAULT_MODEL.LIGHT);

    String example = "임대차기간의 묵시적 갱신이란 무엇인가요?";
    String qnaAnswer = lawQnaApiService.getLegalQA(example);

    KomoranResult komoranResult = komoran.analyze(example);
    KomoranResult komoranResultSecond = komoran.analyze(qnaAnswer);



    List<Token> tokenList = komoranResult.getTokenList();




}
