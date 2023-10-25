package com.example.demo.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "LawSearch")
public class LawClauApiListDTO {

    private String target;

    @JacksonXmlProperty(localName = "키워드")
    private String keyword;
    private String section;
    private Integer totalCnt;
    private Integer page;
    private Integer numOfRows;
    private Integer resultCode;
    private String resultMsg;

    @JacksonXmlProperty(localName = "law")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<LawClauApiDTO> lawClauApiDTOList;


}
