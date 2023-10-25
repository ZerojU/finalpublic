package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//사용하는 메리트가 없어서 안 쓰게 된 서비스
@Service
public class SearchResultService {

    //private List<String> history = new ArrayList<>();

    public List<String> localToView(String searchResult) {
        // 결과 string 을 받아 "," 를 기준으로 쪼갠 뒤 리스트에 저장하고 리턴
        List<String> history = new ArrayList<>(Arrays.asList(searchResult.split(",")));
        System.out.println(history);
        return history;
    }
}
