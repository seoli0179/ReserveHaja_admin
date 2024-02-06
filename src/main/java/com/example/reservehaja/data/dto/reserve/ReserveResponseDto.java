package com.example.reservehaja.data.dto.reserve;

import com.example.reservehaja.data.entity.Reserve;
import com.example.reservehaja.data.entity.RoundCell;
import com.example.reservehaja.data.state.ReserveState;
import com.example.reservehaja.data.state.RoundCellState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReserveResponseDto {

    private Long reserveId;
    private ReserveState reserveState;
    private LocalDateTime reserveStartDate;
    private String username;

    public ReserveResponseDto toEntity(Reserve reserve) {

        this.reserveId = reserve.getId();
        this.reserveState = reserve.getReserveState();
        this.reserveStartDate = reserve.getReserveStartDate();
        this.username = reserve.getUser().getUserName();

        return this;

    }

}
