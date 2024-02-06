package com.example.reservehaja.service.reserve;

import com.example.reservehaja.data.dto.reserve.ReserveResponseDto;
import com.example.reservehaja.data.dto.roundCell.RoundCellResponseDto;
import com.example.reservehaja.data.entity.Reserve;
import com.example.reservehaja.data.entity.RoundCell;
import com.example.reservehaja.data.repo.ReserveRepository;
import com.example.reservehaja.data.repo.RoundCellRepository;
import com.example.reservehaja.data.state.ReserveState;
import com.example.reservehaja.data.state.RoundCellState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReserveService {

    private final ReserveRepository reserveRepository;

    public List<ReserveResponseDto> readReserveList(Long roundCellId) {

        List<ReserveResponseDto> dtos = new ArrayList<>();
        List<Reserve> reserveList = reserveRepository.findByRoundCell_Id(roundCellId);

        for (Reserve reserve : reserveList) {
            ReserveResponseDto dto = new ReserveResponseDto();
            dtos.add(dto.toEntity(reserve));
        }

        return dtos;

    }

    public boolean updateReserveState(Long reserveId, ReserveState reserveState, String username) {

        Optional<Reserve> reserve = reserveRepository.findByIdAndRoundCell_Round_Amenity_Admin_AdminId(reserveId, username);

        if(reserve.isPresent()){
            reserve.get().setReserveState(reserveState);
            reserveRepository.save(reserve.get());
            return true;
        }

        return false;

    }
}
