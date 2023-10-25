package com.example.demo.errorhandler;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


@Getter
@AllArgsConstructor
public enum CustomErrorCode {

    //400 BAD_REQUEST
    INVALID_TEXT_INPUT(BAD_REQUEST, "잘못된 입력입니다. 정확한 검색어를 입력해 주세요."),

    //403 FORBIDDEN
    API_FORBIDDEN_ERROR(FORBIDDEN, "API 호출 중 문제가 발생하였습니다."),

    //500 INTERNAL_SERVER_ERROR
    NO_SEARCH_RESULT(INTERNAL_SERVER_ERROR, "검색 결과가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;

}
