package com.example.reservehaja.service.amenity;

import com.example.reservehaja.data.dao.amenity.AmenityDAO;
import com.example.reservehaja.data.dto.reserve.ReserveRequestDto;
import com.example.reservehaja.data.dto.reserve.ReserveResponseDto;
import com.example.reservehaja.data.dto.reserveInfoJson.ReserveJsonArrayRequestDto;
import com.example.reservehaja.data.entity.Amenity;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AmenityService {

    private final AmenityDAO amenityDAO;

    public boolean addAmenity(ReserveRequestDto reserveRequestDto) {

        return amenityDAO.addAmenity(reserveRequestDto.toEntity());

    }

    public boolean addAmenityJson(ReserveJsonArrayRequestDto requestDto) {

        return amenityDAO.addAmenity(requestDto.toEntity(0));

    }


    public int addAmenityJsonArray(ReserveJsonArrayRequestDto requestDto) {

        int count = 0;

        for (int i = 0; i < requestDto.getRow().size(); i++) {
            if(amenityDAO.addAmenity(requestDto.toEntity(i))){
                count++;
            }
        }

        return count;

    }

    public ReserveResponseDto selectAmenity(Long id) {

        ReserveResponseDto dto = new ReserveResponseDto();

        if(amenityDAO.selectAmenity(id).isPresent()){
            return dto.fromEntity(amenityDAO.selectAmenity(id).get());
        }

        return null;

    }

}
