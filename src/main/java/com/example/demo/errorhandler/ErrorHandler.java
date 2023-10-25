package com.example.demo.errorhandler;



import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    private String handleResponseStatusException(ResponseStatusException e, Model model) {
        log.error("ResponseStatusException occurred");
        e.printStackTrace();
        model.addAttribute("errorCode", e.getStatusCode().value());
        model.addAttribute("errorMessage", e.getReason());
        return "error/noresult";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    private String handleConstraintException(Model model) {
        log.error("user's invalid input error Exception occurred");
        model.addAttribute("errorCode", CustomErrorCode.INVALID_TEXT_INPUT.getHttpStatus().value());
        model.addAttribute("errorMessage", CustomErrorCode.INVALID_TEXT_INPUT.getErrorMessage());
        return "error/invalid";
    }

    @ExceptionHandler(JsonProcessingException.class)
    private String JacksonParsingException(Model model) {
        log.error("jackson parsing error occurred");
        model.addAttribute("errorCode", CustomErrorCode.NO_SEARCH_RESULT.getHttpStatus().value());
        model.addAttribute("errorMessage", CustomErrorCode.NO_SEARCH_RESULT.getErrorMessage());
        return "error/noresult";
    }

}
