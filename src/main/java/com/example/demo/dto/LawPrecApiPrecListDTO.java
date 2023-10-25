package com.example.demo.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;
import java.util.List;


@Data
@JacksonXmlRootElement(localName = "PrecSearch")
public class LawPrecApiPrecListDTO {

    private String target;

    @JacksonXmlProperty(localName = "키워드")
    private String keyword;

    private String section;

    private int totalCnt;

    private int page;

    @JacksonXmlProperty(localName = "prec")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<LawPrecApiPrecDTO> lawPrecApiPrecDTOList;
}
