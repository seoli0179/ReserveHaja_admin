package com.example.reservehaja.data.dao.dto.ReservationInfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Row {
    String GUBUN;
    String SVCID;
    String MAXCLASSNM;
    String MINCLASSNM;
    String SVCSTATNM;
    String SVCNM;
    String PAYATNM;
    String PLACENM;
    String USETGTINFO;
    String SVCURL;
    String X;
    String Y;

    String SVCOPNBGNDT;
    String SVCOPNENDDT;
    String RCPTBGNDT;
    String RCPTENDDT;

    String AREANM;
    String IMGURL;
    String DTLCONT;
    String TELNO;

    //@JsonFormat(pattern = "HH:mm")
    String V_MIN;
    //@JsonFormat(pattern = "HH:mm")
    String V_MAX;

    String REVSTDDAYNM;
    String REVSTDDAY;
}