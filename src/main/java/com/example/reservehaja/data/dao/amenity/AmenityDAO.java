package com.example.reservehaja.data.dao.amenity;

import com.example.reservehaja.data.entity.Amenity;
import com.example.reservehaja.data.repo.AmenityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AmenityDAO {

    private final AmenityRepository amenityRepository;

    public boolean addAmenity(Amenity amenity) {
        if (isEmptyAmenitySvcId(amenity.getSvcId())) {
            amenityRepository.save(amenity);
            return true;
        }
        return false;
    }

    public Optional<Amenity> selectAmenity(Long id) {

        return amenityRepository.findById(id);
    }

    public boolean isEmptyAmenitySvcId(String svcId) {
        return amenityRepository.findBySvcId(svcId).isEmpty();
    }

    public boolean isAmenityId(Long id) {
        return amenityRepository.findById(id).isEmpty();
    }

}
