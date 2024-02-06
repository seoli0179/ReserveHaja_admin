package com.example.reservehaja.data.repo;

import com.example.reservehaja.data.entity.Reserve;
import com.example.reservehaja.data.entity.RoundCell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    //List<Reserve> findByRoundCell_IdAndRoundCell_Round_Amenity_Admin_AdminId(Long id, String adminId);
    List<Reserve> findByRoundCell_Id(Long id);

    Optional<Reserve> findByIdAndRoundCell_Round_Amenity_Admin_AdminId(Long id, String adminId);

}
