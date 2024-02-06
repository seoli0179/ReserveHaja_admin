package com.example.reservehaja.service.round;

import com.example.reservehaja.data.dto.round.RoundResponseDto;
import com.example.reservehaja.data.dto.round.RoundUpdatedRequestDto;
import com.example.reservehaja.data.entity.Amenity;
import com.example.reservehaja.data.entity.Round;
import com.example.reservehaja.data.entity.RoundCell;
import com.example.reservehaja.data.repo.AmenityRepository;
import com.example.reservehaja.data.repo.RoundCellRepository;
import com.example.reservehaja.data.repo.RoundRepository;
import com.example.reservehaja.data.state.RoundCellState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoundService {

    private final RoundRepository roundRepository;

    private final AmenityRepository amenityRepository;

    private final RoundCellRepository roundCellRepository;

    public boolean addRound(Long amenityId, String adminId, Round round) {

        Optional<Amenity> amenity = amenityRepository.findByIdAndAdmin_AdminId(amenityId, adminId);

        if (amenity.isPresent()) {
            round.setAmenity(amenity.get());
            try {
                roundRepository.save(round);
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            // amenity_id와 admin_id가 일치하지 않을 경우 처리
            throw new IllegalStateException("Amenity and Admin IDs do not match.");
        }

        return false;

    }

    public List<RoundResponseDto> readRoundArray(Long id, String username) {
        List<RoundResponseDto> dto = new ArrayList<>();
        List<Round> roundList = roundRepository.findByAmenity_IdAndAmenity_Admin_AdminId(id, username);

        for (int i = 0; i < roundList.size(); i++) {
            dto.add(new RoundResponseDto());
            dto.get(i).fromEntitiy(roundList.get(i));
        }

        return dto;
    }

    public RoundResponseDto readRound(Long id, Long amenityId, String username) {

        RoundResponseDto roundResponseDto = new RoundResponseDto();
        Optional<Round> round = roundRepository.findByIdAndAmenity_IdAndAmenity_Admin_AdminId(id, amenityId, username);

        if (round.isPresent()) {
            /*
            Optional<Amenity> amenity = amenityRepository.findById(amenityId);
            System.out.println(amenity.get().toString());
            if (amenity.isPresent()) {
                round.get().setAmenity(amenity.get());
                return roundResponseDto.fromEntitiy(round.get());
            }

             */
            return roundResponseDto.fromEntitiy(round.get());
        }

        return null;

    }

    public boolean deleteRound(Long id, String username) {
        try {
            if (roundRepository.existsByIdAndAmenity_Admin_AdminId(id, username)) {
                roundRepository.deleteById(id);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Transactional
    public boolean updateRound(RoundUpdatedRequestDto dto, String username) {

        try {
            if (roundRepository.existsByIdAndAmenity_IdAndAmenity_Admin_AdminId(dto.getRoundId(), dto.getAmenityId(), username)) {

                Optional<Round> round = roundRepository.findById(dto.getRoundId());
                if (round.isPresent()) {

                    roundCellRepository.deleteByRoundId(dto.getRoundId());

                    for (int i = 0; i < dto.getRoundDates().size(); i++) {
                        RoundCell roundCell = new RoundCell();
                        roundCell.setRoundCellState(RoundCellState.SERVICE_WAIT);
                        roundCell.setRoundCellDate(dto.getRoundDates().get(i));
                        roundCell.setRound(round.get());
                        roundCellRepository.save(roundCell);
                    }

                    roundRepository.save(dto.toEntity(round.get()));


                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;

    }
}
