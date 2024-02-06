package com.example.reservehaja.data.dto.amenity;

import com.example.reservehaja.data.entity.Amenity;
import com.example.reservehaja.data.state.ServiceState;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class AmenityResponseDto {

    private Long id;
    private String svcId;
    private ServiceState svcStateName; // 서비스 상태
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

    public AmenityResponseDto fromEntity(Amenity amenity) {

        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter pattern2 = DateTimeFormatter.ofPattern("HH:mm");

        id = amenity.getId();
        svcId = amenity.getSvcId();
        svcStateName = amenity.getSvcStateName(); // 서비스 상태
        imageUrl = amenity.getImageUrl();  // 이미지 경로
        svcName = amenity.getSvcName();  // 서비스 명
        placeName = amenity.getPlaceName();  // 장소명
        areaName = amenity.getAreaName();  // 지역명
        userTargetInfo = amenity.getUserTargetInfo();  // 서비스 대상
        rcptBeginDate = amenity.getRcptBeginDate().format(pattern);  // 접수시작일시
        rcptEndDate = amenity.getRcptEndDate().format(pattern);  // 접수종료일시
        svcOpenBeginDate = amenity.getSvcOpenBeginDate().format(pattern);  // 서비스개시 시작일시
        svcOpenEndDate = amenity.getSvcOpenEndDate().format(pattern);  // 서비스개시 종료일
        svcUseBeginTime = amenity.getSvcUseBeginTime().format(pattern2);  // 서비스이용 시작시간
        svcUseEndTime = amenity.getSvcUseEndTime().format(pattern2);
        placeX = amenity.getPlaceX();  // 장소X좌표
        placeY = amenity.getPlaceY();  // 장소Y좌표
        detailInfo = amenity.getDetailInfo();  // 상세정보
        revokeStandDayName = amenity.getRevokeStandDayName();

        return this;

    }

}
