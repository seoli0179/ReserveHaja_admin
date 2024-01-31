package com.example.reservehaja.data.dto.reserve;

import com.example.reservehaja.data.entity.Amenity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ReserveListResponseDto {

    private Long id;
    private String svcStateName; // 서비스 상태
    private String imageUrl; // 이미지 경로
    private String svcName; // 서비스 명
    private String placeName; // 장소명
    private String areaName; // 지역명
    private String svcOpenBeginDate; // 서비스개시 시작일시
    private String svcOpenEndDate; // 서비스개시 종료일시

    public ReserveListResponseDto fromEntity(Amenity amenity) {

        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

        id = amenity.getId();
        svcStateName = amenity.getSvcStateName().getLabel(); // 서비스 상태
        imageUrl = amenity.getImageUrl();  // 이미지 경로
        svcName = amenity.getSvcName();  // 서비스 명
        placeName = amenity.getPlaceName();  // 장소명
        areaName = amenity.getAreaName();  // 지역명
        svcOpenBeginDate = amenity.getSvcOpenBeginDate().format(pattern);  // 접수시작일시
        svcOpenEndDate = amenity.getSvcOpenEndDate().format(pattern);  // 접수종료일시

        return this;

    }

}
