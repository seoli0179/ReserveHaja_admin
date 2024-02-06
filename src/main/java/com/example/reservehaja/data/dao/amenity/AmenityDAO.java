package com.example.reservehaja.data.dao.amenity;

import com.example.reservehaja.data.dto.amenity.AmenityUpdateRequestDto;
import com.example.reservehaja.data.entity.Amenity;
import com.example.reservehaja.data.repo.AmenityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public Optional<Amenity> selectAmenityWhereAdminId(Long id, String adminId) {
        return amenityRepository.findByIdAndAdmin_AdminId(id, adminId);
    }

    public List<Amenity> selectAmenityList(String adminId) {
        return amenityRepository.findByAdmin_AdminId(adminId);
    }

    public boolean updateAmenity(AmenityUpdateRequestDto dto) {

        try {

            Optional<Amenity> amenity = amenityRepository.findById(dto.getId());

            if (amenity.isPresent()) {
                amenityRepository.save(dto.toEntity(amenity.get()));
                return true;
            }

            return false;

        } catch (Exception e) {
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

    public boolean isAmenityIdAndAdminId(Long id, String adminId) {
        return amenityRepository.findByIdAndAdmin_AdminId(id, adminId).isPresent();
    }

    @Transactional
    public boolean deleteAmenity(Long id, String adminId) {
        try {
            if (isAmenityIdAndAdminId(id, adminId)) {
                amenityRepository.deleteAmenityByIdAndAdmin_AdminId(id, adminId);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
