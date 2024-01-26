package com.example.reservehaja.data.dto.reserve;

import com.example.reservehaja.data.entity.Amenity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ReserveUpdateRequestDto {

    private Long id;
    private String svcStateName; // 서비스 상태
    private String imageUrl; // 이미지 경로
    private String svcName; // 서비스 명
    private String placeName; // 장소명
    private String areaName; // 지역명
    private String userTargetInfo; // 서비스 대상
    private String rcptBeginDate; // 접수시작일시
    private String rcptEndDate; // 접수종료일시
    private String svcOpenBeginDate; // 서비스개시 시작일시
    private String svcOpenEndDate; // 서비스개시 종료일시
    private String svcUseBeginTime; // 서비스이용 시작시간
    private String svcUseEndTime; // 서비스이용 종료시간
    private String placeX; // 장소X좌표
    private String placeY; // 장소Y좌표
    private String detailInfo; // 상세정보
    private String revokeStandDayName; // 취소기간 기준정보

    public Amenity toEntity(Amenity amenity) {

        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter pattern2 = DateTimeFormatter.ofPattern("HH:mm");

        amenity.setSvcStateName(svcStateName); // 서비스 상태
        amenity.setImageUrl(imageUrl);  // 이미지 경로
        amenity.setSvcName(svcName);  // 서비스 명
        amenity.setPlaceName(placeName);  // 장소명
        amenity.setAreaName(areaName);  // 지역명
        amenity.setUserTargetInfo(userTargetInfo);  // 서비스 대상
        amenity.setRcptBeginDate(LocalDateTime.parse(rcptBeginDate, pattern));  // 접수시작일시
        amenity.setRcptEndDate(LocalDateTime.parse(rcptEndDate, pattern));  // 접수종료일시
        amenity.setSvcOpenBeginDate(LocalDateTime.parse(svcOpenBeginDate, pattern));  // 서비스개시 시작일시
        amenity.setSvcOpenEndDate(LocalDateTime.parse(svcOpenEndDate, pattern));  // 서비스개시 종료일
        amenity.setSvcUseBeginTime(LocalTime.parse(svcUseBeginTime, pattern2));  // 서비스이용 시작시간
        amenity.setSvcUseEndTime(LocalTime.parse(svcUseEndTime, pattern2));
        amenity.setPlaceX(placeX);  // 장소X좌표
        amenity.setPlaceY(placeY);  // 장소Y좌표
        amenity.setDetailInfo(detailInfo);  // 상세정보
        amenity.setRevokeStandDayName(revokeStandDayName);

        return amenity;

    }

}
