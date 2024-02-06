package com.example.reservehaja.data.repo;

import com.example.reservehaja.data.dto.round.RoundResponseDto;
import com.example.reservehaja.data.entity.RoundCell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoundCellRepository extends JpaRepository<RoundCell, Long> {

    void deleteByRoundId(Long roundId);

    List<RoundCell> findByRound_IdAndRound_Amenity_Admin_AdminId(Long roundId, String adminId);

    Optional<RoundCell> findByIdAndRound_Amenity_Admin_AdminId(Long id, String adminId);

}
