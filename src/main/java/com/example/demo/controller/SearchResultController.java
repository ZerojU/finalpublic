package com.example.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
//@Log4j2
//@RequestMapping("/lss/search")
//public class SearchResultController {

    // 사용하지 않는 컨트롤러
    // 본래 ajax를 통해 controller로 하여금 localstorage를 받아 파싱, 다시 화면에 보내주는 역할을 하는 컨트롤러였음
    // 그런데 굳이 서버로 전달할 필요가 없다는 피드백을 받고 통째로 잘라냄(0927).... 왜그랬을까?


    // 구현의 경우 ajax를 통해 request를 보낸 뒤 controller에서 받아 model을 통해 화면에 전달하는 방식으로 구현하려 했음
    // 그러나 새로고침을 두 번 해야 최신 정보가 나타나는 현상이 발생....
    // 찾아보니 PRG 패턴이라는 것이 있음
    // lss/history(get) > history.mustache의 js 파일을 통해 ajax request(post) > lss/history(get) 과정에서
    // 걍 get으로 화면을 불러오기 때문에 최초에 텅 비어있고 새로고침을 여러번 해야 했던 것임..

    // 문제 해결을 위해 lss/search/result 페이지를 만들고 리다이렉트를 시켜주기로 함
    // 그러나 화면에 아무리 list를 뿌려주어도 mustache가 반응하지 않음... 리다이렉트 하면 페이지가 lss/history로 옮겨가야 하는데 안옮겨짐...
    // 검색해보니 request를 보낸 주체가 화면단이라, 페이지를 변경할 권한은 controller에서 가지고 있지 않다고 함...(맞는지 모르겠음)
    // 때문에 200 결과를 리턴하고 javascript에서 리다이렉트할 url을 연결해 줌
    // 페이지에 js를 둘다 선언해서 페이지를 서로 주고받아 무한새로고침되는 작은 이슈 있었으나 해결
    // 근데 그랬음에도 딱히 화면에 list가 뿌려지지 않음
    // 컨트롤러 간에 list를 전달해보려고 혼신의 똥꼬쑈함
    // 안됨...
    // 이쯤에서 멘토분께 문제해결을 요청했고, "다 제쳐두고 서버로 보낼 이유가 없는 것 같다"는 피드백을 들음
    // 오........... 그러게요 ...? ???-T
    // 그래서 필요없어짐

    //@Autowired
    //private SearchResultService searchResultService;

    //private List<String> searchResult = new ArrayList<>();

//    @GetMapping("/result")
//    public void getResultListPage() {
//        // 오직 post만을 위한 빈 페이지 오픈
//        log.info("post page for only history opened");
//    }

//    @PostMapping("/history")
//    public String getSearchResultList(@RequestBody Map<String, Object> requestData, RedirectAttributes redirectAttributes) {
//        log.info("law search history page opened");
//
//        String searchHistory = String.valueOf(requestData.get("history"));
//        log.info(searchHistory);
//
//        searchHistoryList = searchResultService.localToView(String.valueOf(requestData.get("history")));
//        //model.addAttribute("searchHistoryList", searchHistoryList);
//        redirectAttributes.addAttribute("searchHistoryList", searchHistoryList);
//        log.info("controller search history send");
//
//        return "redirect:search/result";
//
//
//    }

//    @PostMapping("/result")
//    public ResponseEntity<Map<String, Object>> getSearchResultList(
//            Model model, @RequestBody Map<String, Object> requestData) {
//        log.info("law search history page opened");
//
//        // 화면에서 들어온 데이터를 object로 받았으므로 string으로 변환하여 저장
//        String searchHistory = String.valueOf(requestData.get("history"));
//        log.info(searchHistory);
//
//        // 화면에서 들어온 데이터를 string으로 바꾼 뒤 리스트로 변환
//        searchResult = searchResultService.localToView(String.valueOf(requestData.get("history")));
//        //model.addAttribute("searchResult", searchResult);
//        //redirectAttributes.addFlashAttribute("searchResult", searchResult);
//        //redirectAttributes.addAttribute("searchHistory", searchHistoryList);
//
//        // 리다이렉트 위치를 선언
//        Map<String, Object> responseData = new HashMap<>();
//        responseData.put("redirectUrl", "../history");
//        responseData.put("searchResult", searchResult);
//
//        // 최초 들어온 요청에 200 response 전달
//        return ResponseEntity.ok(responseData);
//    }


//    @PostMapping("/result")
//    public void getSearchResultList(
//            Model model, @RequestBody Map<String, String> requestData) {
//        log.info("law search history page opened");
//
//        String searchHistory = requestData.get("history");
//        log.info(searchHistory);
//
//        searchResult = searchResultService.localToView(requestData.get("history"));
//        model.addAttribute("searchResult", searchResult);
//        //redirectAttributes.addAttribute("searchResult", searchResult);
//        log.info("searchResult send");
//
//    }

//}



