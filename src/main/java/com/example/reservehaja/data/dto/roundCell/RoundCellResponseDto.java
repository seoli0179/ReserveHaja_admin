package com.example.reservehaja.data.dto.roundCell;

import com.example.reservehaja.data.entity.Reserve;
import com.example.reservehaja.data.entity.Round;
import com.example.reservehaja.data.entity.RoundCell;
import com.example.reservehaja.data.state.ReserveState;
import com.example.reservehaja.data.state.RoundCellState;
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
public class RoundCellResponseDto {

    private Long roundCellId;
    private RoundCellState roundCellState;
    private LocalDate roundCellDate;
    private String roundName;
    private int judge;
    private int cancel;

    public RoundCellResponseDto toEntity(RoundCell roundCell) {

        //RoundCell roundCell = new RoundCell();

        this.roundCellId = roundCell.getId();
        this.roundCellState = roundCell.getRoundCellState();
        this.roundCellDate = roundCell.getRoundCellDate();

        judge = 0;
        cancel = 0;


        for (Reserve reserve : roundCell.getReserveList()) {
            switch (reserve.getReserveState()){
                case RESERVE_JUDGE -> {
                    judge++;
                    break;
                }case RESERVE_CANCEL -> {
                    cancel++;
                    break;
                }
            }

        }

        this.roundName = roundCell.getRound().getRoundName();

        return this;

    }

}
