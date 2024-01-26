package com.example.reservehaja.data.dao.amenity;

import com.example.reservehaja.data.dto.reserve.ReserveUpdateRequestDto;
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

    public boolean updateAmenity(ReserveUpdateRequestDto dto) {

        try {

            Optional<Amenity> amenity = amenityRepository.findById(dto.getId());

            if(amenity.isPresent()) {
                amenityRepository.save(dto.toEntity(amenity.get()));
                return true;
            }

            return false;

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public boolean isEmptyAmenitySvcId(String svcId) {
        return amenityRepository.findBySvcId(svcId).isEmpty();
    }

    public boolean isAmenityId(Long id) {
        return amenityRepository.findById(id).isPresent();
    }

    public boolean deleteAmenity(Long id) {
        try {
            if(isAmenityId(id)){
                amenityRepository.deleteById(id);
                return true;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
