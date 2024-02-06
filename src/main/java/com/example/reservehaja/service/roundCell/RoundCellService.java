package com.example.reservehaja.service.roundCell;

import com.example.reservehaja.data.dto.round.RoundResponseDto;
import com.example.reservehaja.data.dto.roundCell.RoundCellResponseDto;
import com.example.reservehaja.data.entity.Round;
import com.example.reservehaja.data.entity.RoundCell;
import com.example.reservehaja.data.repo.RoundCellRepository;
import com.example.reservehaja.data.state.RoundCellState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoundCellService {

    private final RoundCellRepository roundCellRepository;

    public boolean addRoundCell(RoundCell roundCell) {
        try {
            roundCellRepository.save(roundCell);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<RoundCellResponseDto> readRoundCellList(Long roundId, String username) {

        List<RoundCellResponseDto> dtos = new ArrayList<>();
        List<RoundCell> roundCellList = roundCellRepository.findByRound_IdAndRound_Amenity_Admin_AdminId(roundId, username);

        for (RoundCell roundCell : roundCellList) {
            RoundCellResponseDto dto = new RoundCellResponseDto();
            dtos.add(dto.toEntity(roundCell));
        }

        return dtos;

    }

    public boolean updateRoundCellState(Long roundCellId, RoundCellState roundCellState, String username) {

        Optional<RoundCell> roundCell = roundCellRepository.findByIdAndRound_Amenity_Admin_AdminId(roundCellId, username);

        if(roundCell.isPresent()){
            roundCell.get().setRoundCellState(roundCellState);
            roundCellRepository.save(roundCell.get());
            return true;
        }

        return false;

    }
}
