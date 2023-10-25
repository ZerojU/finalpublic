package com.example.demo.dto;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "law")
public class LawClauApiDTO {

    @JacksonXmlProperty(isAttribute = true)
    private int id;

    @JacksonXmlProperty(localName = "법령일련번호")
    private String clauSerial;

    @JacksonXmlProperty(localName = "현행연혁코드")
    private String presentHisCode;

    @JacksonXmlProperty(localName = "법령명한글")
    private String clauKoOrigin;

    @JacksonXmlProperty(localName = "법령약칭명")
    private String clauKoShort;

    @JacksonXmlProperty(localName = "법령ID")
    private String clauID;

    @JacksonXmlProperty(localName = "공포일자")  //promulgation 공포
    private String promulDate;

    @JacksonXmlProperty(localName = "공포번호")
    private String promulNum;

    @JacksonXmlProperty(localName = "제개정구분명")
    private String reClassificName;

    @JacksonXmlProperty(localName = "소관부처코드")
    private String ministryCode;

    @JacksonXmlProperty(localName = "소관부처명")
    private String ministry;

    @JacksonXmlProperty(localName = "법령구분명")
    private String specificLaw;

    @JacksonXmlProperty(localName = "시행일자")
    private String enforceDate;

    @JacksonXmlProperty(localName = "자법타법여부")  //여부이긴 한데 boolean 값이 아닌듯함
    private String selfOrOtherClassific;

    @JacksonXmlProperty(localName = "법령상세링크")
    private String clauViewLink;

}
