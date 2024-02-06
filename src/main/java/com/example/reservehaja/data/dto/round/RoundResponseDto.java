package com.example.reservehaja.data.dto.round;

import com.example.reservehaja.data.entity.Amenity;
import com.example.reservehaja.data.entity.Round;
import com.example.reservehaja.data.entity.RoundCell;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class RoundResponseDto {

    private Long id;
    private String roundName;
    private String roundUseBeginTime;
    private String roundUseEndTime;
    private Amenity amenity;
    private List<RoundCell> roundCellList;

    public RoundResponseDto fromEntitiy(Round round) {

        Amenity amenity = new Amenity();

        this.id = round.getId();
        this.roundName = round.getRoundName();
        this.roundUseBeginTime = round.getRoundUseBeginTime().toString();
        this.roundUseEndTime = round.getRoundUseEndTime().toString();
        amenity.setSvcName(round.getAmenity().getSvcName());
        amenity.setSvcOpenBeginDate(round.getAmenity().getSvcOpenBeginDate());
        amenity.setSvcOpenEndDate(round.getAmenity().getSvcOpenEndDate());

        roundCellList = new ArrayList<>();

        for (RoundCell roundCell : round.getRoundCellList()){
            RoundCell roundCell1 = new RoundCell();
            roundCell1.setId(roundCell.getId());
            roundCell1.setRoundCellState(roundCell.getRoundCellState());
            roundCell1.setRoundCellDate(roundCell.getRoundCellDate());
            roundCellList.add(roundCell1);
        }
        this.amenity = amenity;

        return this;

    }

}
