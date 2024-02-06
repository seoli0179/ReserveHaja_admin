package com.example.reservehaja.data.dto.round;

import com.example.reservehaja.data.entity.Admin;
import com.example.reservehaja.data.entity.Amenity;
import com.example.reservehaja.data.entity.Round;
import com.example.reservehaja.data.state.ServiceState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@ToString
public class RoundRequestDto {

    private Long amenityId;
    private String roundName;
    private List<LocalDate> roundDates;
    private String roundUseBeginTime;
    private String roundUseEndTime;

    public Round toEntity() {

        DateTimeFormatter pattern2 = DateTimeFormatter.ofPattern("HH:mm");

        Round round = new Round();

        round.setRoundName(roundName);  // 이미지 경로
        round.setRoundUseBeginTime(LocalTime.parse(roundUseBeginTime, pattern2));  // 접수시작일시
        round.setRoundUseEndTime(LocalTime.parse(roundUseEndTime, pattern2));  // 접수종료일시

        return round;

    }

}
