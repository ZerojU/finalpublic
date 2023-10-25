package com.example.demo.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

//쿠키 안쓰게 돼서 안쓰게 된 서비스
@Service
public class SearchResultToCookieService {

    private Cookie cookie = new Cookie("LSS-SRTC", "");
    private final HttpServletResponse httpServletResponse;
    private final HttpServletRequest httpServletRequest;

    //private Set<String> duplicatedResult = new HashSet<>();

    private String data = null;

    @Autowired
    public SearchResultToCookieService(
            HttpServletResponse httpServletResponse,
            HttpServletRequest httpServletRequest) {
        this.httpServletResponse = httpServletResponse;
        this.httpServletRequest = httpServletRequest;
    }

    public void setCookie(String searchResult) {

        if (this.cookie.getValue() == "") {
            this.cookie.setValue(searchResult);
        } else {
            //기존의 쿠키 값에 현재 검색어를 추가하여 지속적으로 value 값을 갱신하는 방식
            String data = this.cookie.getValue() + "/" + searchResult;
            this.cookie.setValue(data);
        }
        //쿠키를 추가
        httpServletResponse.addCookie(cookie);
    }

    public void dupErasedCookie(String searchResult) {
        Set<String> duplicatedResult = new HashSet<>();

        if (Objects.equals(this.cookie.getValue(), "")) {
            this.cookie.setValue(searchResult);
            duplicatedResult.add(searchResult);
        } else {
            duplicatedResult.add(searchResult);

            for (String dupErasedWord : duplicatedResult) {
                this.data += dupErasedWord + "/";
                System.out.println("this.data" + this.data);
            }
            //String data = this.cookie.getValue() + "/" + searchResult;
            this.cookie.setValue(data);
        }
        //String data = null;

//        if (this.cookie.getValue() == "") {
//            this.cookie.setValue(searchResult);
//            duplicatedResult.add(searchResult);
//        } else {
//            duplicatedResult.add(searchResult);
//            //첫 값이 존재하니까
//            for (String dupErasedWord : duplicatedResult) {
//                data = "/" + dupErasedWord;
//            }
//            this.cookie.setValue(data);
//        }
        httpServletResponse.addCookie(this.cookie);
    }

    public void setCookie(String searchResult, int expireDate) {
        this.cookie = new Cookie("LSS-SR", searchResult);
        cookie.setMaxAge(expireDate);
        httpServletResponse.addCookie(cookie);
    }

    public void expiredCookie(Cookie cookieName) {
        //만료 시간을 초기화 하여 쿠키를 부숨..
        cookieName.setMaxAge(0);
        httpServletResponse.addCookie(cookieName);
    }

    public String getValueFromCookie(String cookieName) {
        Cookie[] cookies = httpServletRequest.getCookies();

        if (cookies != null) {
            for (Cookie ck : cookies) {
                if (ck.getName().equals(cookieName)) {
                    System.out.println("cookieValue : " + ck.getValue());
                    return ck.getValue();
                }
            }
        }
        return null;
    }

    public Set<String> getSearchResultFromCookie(String cookieName) {
        Cookie[] cookies = httpServletRequest.getCookies();

        //새로고침마다 중복되는 것을 피하기 위해 함수 안에서 새로 선언
        Set<String> searchResultList = new HashSet<>();
        //쿠키 값 초기화
        String cookieValue = null;

        if (cookies != null) { //쿠키가 존재할 때에
            for (Cookie ck : cookies) {
                if (ck.getName().equals(cookieName)) {  //입력한 이름과 같다면
                    System.out.println("cookieValue : " + ck.getValue());
                    cookieValue = ck.getValue(); //쿠키 값을 가져와 저장
                }
            }
        }
        // "/" 을 기준으로 나누어 리스트에 저장한 뒤 리턴
        String[] searchResultSpilt = cookieValue.split("/");
        searchResultList.addAll(Arrays.asList(searchResultSpilt));
        return searchResultList;
    }





}
