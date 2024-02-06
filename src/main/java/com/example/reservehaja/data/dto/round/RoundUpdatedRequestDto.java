package com.example.reservehaja.data.dto.round;

import com.example.reservehaja.data.entity.Round;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@ToString
public class RoundUpdatedRequestDto {

    private Long amenityId;
    private Long roundId;
    private String roundName;
    private List<LocalDate> roundDates;
    private String roundUseBeginTime;
    private String roundUseEndTime;

    public Round toEntity(Round round) {

        DateTimeFormatter pattern2 = DateTimeFormatter.ofPattern("HH:mm");

        round.setId(roundId);
        round.setRoundName(roundName);  // 이미지 경로
        round.setRoundUseBeginTime(LocalTime.parse(roundUseBeginTime, pattern2));  // 접수시작일시
        round.setRoundUseEndTime(LocalTime.parse(roundUseEndTime, pattern2));  // 접수종료일시

        return round;

    }

}
