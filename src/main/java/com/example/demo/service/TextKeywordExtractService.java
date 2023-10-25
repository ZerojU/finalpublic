package com.example.demo.service;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Log4j2
@Service
public class TextKeywordExtractService {

    // 근거에서 명사를 추출한 뒤 가장 많이 나온 최고 3개를 키워드로 추천..은 아니고 추출하는 서비스
    // Komoran 형태소 분석기를 통해서 명사를 추출해 키워드를 판단합니다.
    // default model은 full, light 모델이 있으나 light를 기준으로 사용합니다.

    private final Komoran komoran;

    @Autowired
    public TextKeywordExtractService() {
        this.komoran = new Komoran(DEFAULT_MODEL.LIGHT);
    }

    public List<String> getKeyword(String clause) {

        KomoranResult komoranResult = komoran.analyze(clause);
        log.info("sentence analyzed");

        //문장에서 중복을 포함하는 명사를 뽑아서 리스트에 저장
        List<String> duplicateNouns = komoranResult.getNouns();

        // "단어" "중복 횟수" 로 구성된 해시맵 생성
        Map<String, Integer> dupErasedNouns = new HashMap<>();

        //키워드를 담을 리스트를 생성
        List<String> extractKeywords = new ArrayList<>();

        //반복하여 중복 횟수를 더함
        for(String noun : duplicateNouns) {
            if (dupErasedNouns.containsKey(noun)) {
                dupErasedNouns.put(noun, dupErasedNouns.get(noun) + 1);
            } else {
                dupErasedNouns.put(noun, 1);
            }
        }

        // HashMap을 내림차순으로 정렬한 후 리스트로 변환
        List<Map.Entry<String, Integer>> orderedList = new ArrayList<>(dupErasedNouns.entrySet());

        // Comparator를 사용하여 내림차순 정렬
        orderedList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        log.info("noun list ordered");

        //탑 3만 키워드 목록에 추가함
        extractKeywords.add(orderedList.get(0).getKey());
        extractKeywords.add(orderedList.get(1).getKey());
        extractKeywords.add(orderedList.get(2).getKey());
        log.info("top 3 noun extracted and added");

        return extractKeywords;
    }





}
