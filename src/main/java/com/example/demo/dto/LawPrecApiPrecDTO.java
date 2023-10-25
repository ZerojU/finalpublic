package com.example.demo.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "prec")
public class LawPrecApiPrecDTO {

    @JacksonXmlProperty(isAttribute = true)
    private int id;

    @JacksonXmlProperty(localName = "판례일련번호")
    private String precSerial;

    @JacksonXmlProperty(localName = "사건명")
    private String caseName;

    @JacksonXmlProperty(localName = "사건번호")
    private String caseNum;

    @JacksonXmlProperty(localName = "선고일자")
    private String sentenceDate;

    @JacksonXmlProperty(localName = "법원명")
    private String courtName;

    @JacksonXmlProperty(localName = "법원종류코드")
    private String countCode;

    @JacksonXmlProperty(localName = "사건종류명")
    private String caseType;

    @JacksonXmlProperty(localName = "사건종류코드")
    private String caseTypeCode;

    @JacksonXmlProperty(localName = "판결유형")
    private String judgeType;

    @JacksonXmlProperty(localName = "선고")
    private String sentence;

    @JacksonXmlProperty(localName = "판례상세링크")
    private String precViewLink;
}
