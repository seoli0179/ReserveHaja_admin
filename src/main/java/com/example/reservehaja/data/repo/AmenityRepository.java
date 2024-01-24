package com.example.reservehaja.data.repo;

import com.example.reservehaja.data.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AmenityRepository extends JpaRepository<Amenity,Long> {
    Optional<Amenity> findBySvcId(String svcId);
}
