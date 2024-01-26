package com.example.reservehaja.data.dto.reservationInfoJson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ReservationInfoResponseDto {

    private int list_total_count;
    private Result RESULT;
    private List<Row> row;

}
