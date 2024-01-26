package com.example.reservehaja.data.dto.reserveInfoJson;

import com.example.reservehaja.data.entity.Amenity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor //swagger에서 없으면 못읽어옴(null 에러)
public class ReserveJsonArrayRequestDto {

    private int list_total_count;
    private Result RESULT;
    private List<Row> row;

    public Amenity toEntity(int index) {

        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter pattern2 = DateTimeFormatter.ofPattern("HH:mm");

        Amenity amenity = new Amenity();

        amenity.setSvcId(row.get(index).getSVCID()); // 서비스Id
        amenity.setSvcStateName(row.get(index).getSVCSTATNM()); // 서비스 상태
        amenity.setImageUrl(row.get(index).getIMGURL());  // 이미지 경로
        amenity.setSvcName(row.get(index).getSVCNM());  // 서비스 명
        amenity.setPlaceName(row.get(index).getPLACENM());  // 장소명
        amenity.setAreaName(row.get(index).getAREANM());  // 지역명
        amenity.setUserTargetInfo(row.get(index).getUSETGTINFO());  // 서비스 대상
        amenity.setRcptBeginDate(LocalDateTime.parse(row.get(index).getRCPTBGNDT(), pattern));  // 접수시작일시
        amenity.setRcptEndDate(LocalDateTime.parse(row.get(index).getRCPTENDDT(), pattern));  // 접수종료일시
        amenity.setSvcOpenBeginDate(LocalDateTime.parse(row.get(index).getSVCOPNBGNDT(), pattern));  // 서비스개시 시작일시
        amenity.setSvcOpenEndDate(LocalDateTime.parse(row.get(index).getSVCOPNENDDT(), pattern));  // 서비스개시 종료일
        amenity.setSvcUseBeginTime(LocalTime.parse(row.get(index).getV_MIN(), pattern2));  // 서비스이용 시작시간
        amenity.setSvcUseEndTime(LocalTime.parse(row.get(index).getV_MAX(), pattern2));
        amenity.setPlaceX(row.get(index).getX());  // 장소X좌표
        amenity.setPlaceY(row.get(index).getY());  // 장소Y좌표
        amenity.setDetailInfo(row.get(index).getDTLCONT());  // 상세정보
        amenity.setRevokeStandDayName(row.get(index).getREVSTDDAYNM());

        return amenity;

    }

}
