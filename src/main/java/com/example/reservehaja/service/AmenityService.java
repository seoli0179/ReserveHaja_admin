package com.example.reservehaja.service;

import com.example.reservehaja.data.dao.dto.ReservationInfo.ReservationInfoResponseDto;
import com.example.reservehaja.data.entity.Amenity;
import com.example.reservehaja.data.repo.AmenityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class AmenityService {

    private final AmenityRepository amenityRepository;

    public void addAmenityArray(ReservationInfoResponseDto requestDto) {

        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter pattern2 = DateTimeFormatter.ofPattern("HH:mm");


        System.out.println(requestDto.getRow().size());

        for (int i = 0; i < requestDto.getRow().size(); i++) {

            if(amenityRepository.findBySvcId(requestDto.getRow().get(i).getSVCID()).isEmpty()){
                Amenity amenity = new Amenity();

                System.out.println(requestDto.getRow().get(i).getSVCID());

                amenity.setSvcId(requestDto.getRow().get(i).getSVCID()); // 서비스Id
                amenity.setSvcStateName(requestDto.getRow().get(i).getSVCSTATNM()); // 서비스 상태
                amenity.setImageUrl(requestDto.getRow().get(i).getIMGURL());  // 이미지 경로
                amenity.setSvcName(requestDto.getRow().get(i).getSVCNM());  // 서비스 명
                amenity.setPlaceName(requestDto.getRow().get(i).getPLACENM());  // 장소명
                amenity.setAreaName(requestDto.getRow().get(i).getAREANM());  // 지역명
                amenity.setUserTargetInfo(requestDto.getRow().get(i).getUSETGTINFO());  // 서비스 대상
                amenity.setRcptBeginDate(LocalDateTime.parse(requestDto.getRow().get(i).getRCPTBGNDT(),pattern));  // 접수시작일시
                amenity.setRcptEndDate(LocalDateTime.parse(requestDto.getRow().get(i).getRCPTENDDT(),pattern));  // 접수종료일시
                amenity.setSvcOpenBeginDate(LocalDateTime.parse(requestDto.getRow().get(i).getSVCOPNBGNDT(),pattern));  // 서비스개시 시작일시
                amenity.setSvcOpenEndDate(LocalDateTime.parse(requestDto.getRow().get(i).getSVCOPNENDDT(),pattern));  // 서비스개시 종료일
                amenity.setSvcUseBeginTime(LocalTime.parse(requestDto.getRow().get(i).getV_MIN(),pattern2));  // 서비스이용 시작시간
                amenity.setSvcUseEndTime(LocalTime.parse(requestDto.getRow().get(i).getV_MAX()));
                amenity.setPlaceX(requestDto.getRow().get(i).getX());  // 장소X좌표
                amenity.setPlaceY(requestDto.getRow().get(i).getY());  // 장소Y좌표
                amenity.setDetailInfo(requestDto.getRow().get(i).getDTLCONT());  // 상세정보
                amenity.setRevokeStandDayName(requestDto.getRow().get(i).getREVSTDDAYNM());

                amenityRepository.save(amenity);
            }

        }

    }

}
