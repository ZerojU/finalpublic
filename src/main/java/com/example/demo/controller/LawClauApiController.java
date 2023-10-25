package com.example.demo.controller;

import com.example.demo.dto.LawClauApiDTO;
import com.example.demo.dto.LawClauApiListDTO;
import com.example.demo.dto.PageDTO;
import com.example.demo.service.ApiPagingService;
import com.example.demo.service.LawClauApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Log4j2
@Validated
@RequestMapping("/search")
public class LawClauApiController {

    @Autowired
    private LawClauApiService lawClauApiService;

    @Autowired
    private ApiPagingService pagingService;

    private PageDTO pageDTO = new PageDTO(1, 1);

    @GetMapping("/clause")
    public void getLawClauList(
            @RequestParam("clause-query") @Size(max = 200) String clauQuery,
            @RequestParam("clauType") String clauType,
            @RequestParam(value = "page", defaultValue = "1")
            @Positive(message = "정수만 가능합니다.") @Min(value = 1, message = "최소 페이지는 1 페이지입니다.") String page,
            Model model) {
        log.info("law clause list search result page opened");

        //페이지 최대 갯수보다 큰 값이 들어오면 강제로 마지막 페이지로 바꿈
        if (Integer.parseInt(page) > pageDTO.getLastPage()) {
            page = String.valueOf(pageDTO.getLastPage());
        }

        //결과 xml을 가져옴
        String resultClauseXml = lawClauApiService.getClausesFromLaws(clauQuery, Integer.parseInt(clauType), Integer.parseInt(page));
        ObjectMapper objectMapper = new XmlMapper();

        //키워드를 페이지로 전송
        model.addAttribute("clauQuery", clauQuery);

        try {
            //objectmapper를 통해 xml을 dto 객체로 다시 받음
            LawClauApiListDTO clauListDTO = objectMapper.readValue(resultClauseXml, LawClauApiListDTO.class);
            List<LawClauApiDTO> clauDTO = clauListDTO.getLawClauApiDTOList();

            log.info("law clause result page -- xml response successfully parsed");

            //파싱한 객체 리스트와 카운트 값을 페이지로 전송
            model.addAttribute("clauList", clauDTO);
            model.addAttribute("clauType", clauType);
            model.addAttribute("totalCount", clauListDTO.getTotalCnt());

            pageDTO = pagingService.setPaging(clauListDTO.getTotalCnt(), Integer.parseInt(page));

            //페이지 객체를 페이징을 위해 전송
            model.addAttribute("pageDTO", pageDTO);
            log.info("pagedto created and page initialized");

        } catch (JsonProcessingException e) {
            log.error("Law Clause Api Json Processing Exception occurred");
            e.printStackTrace();
        }
    }
}
