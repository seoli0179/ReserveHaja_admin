package com.example.reservehaja.data.repo;

import com.example.reservehaja.data.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoundRepository extends JpaRepository<Round, Long> {
    Optional<Round> findByIdAndAmenity_IdAndAmenity_Admin_AdminId(Long id, Long amenityId, String adminId);

    boolean existsByIdAndAmenity_Admin_AdminId(Long id, String adminId);

    boolean existsByIdAndAmenity_IdAndAmenity_Admin_AdminId(Long id, Long amenityId, String adminId);

    List<Round> findByAmenity_IdAndAmenity_Admin_AdminId(Long id, String adminId);

    void deleteByAmenity_Id(Long amenityId);

}
