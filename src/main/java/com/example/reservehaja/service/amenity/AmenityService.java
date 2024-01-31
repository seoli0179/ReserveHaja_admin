package com.example.reservehaja.service.amenity;

import com.example.reservehaja.data.dao.admin.AdminDAO;
import com.example.reservehaja.data.dao.amenity.AmenityDAO;
import com.example.reservehaja.data.dto.reserve.ReserveListResponseDto;
import com.example.reservehaja.data.dto.reserve.ReserveRequestDto;
import com.example.reservehaja.data.dto.reserve.ReserveResponseDto;
import com.example.reservehaja.data.dto.reserve.ReserveUpdateRequestDto;
import com.example.reservehaja.data.dto.reserveInfoJson.ReserveJsonArrayRequestDto;
import com.example.reservehaja.data.entity.Admin;
import com.example.reservehaja.data.entity.Amenity;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AmenityService {

    private final AmenityDAO amenityDAO;
    private final AdminDAO adminDAO;

    public boolean addAmenity(ReserveRequestDto reserveRequestDto, String username) {
        Optional<Admin> admin = adminDAO.findAdmin(username);
        return admin.filter(value -> amenityDAO.addAmenity(reserveRequestDto.toEntity(value))).isPresent();
    }

    public boolean addAmenityJson(ReserveJsonArrayRequestDto requestDto) {

        return amenityDAO.addAmenity(requestDto.toEntity(0));

    }


    public int addAmenityJsonArray(ReserveJsonArrayRequestDto requestDto) {

        int count = 0;

        for (int i = 0; i < requestDto.getRow().size(); i++) {
            if (amenityDAO.addAmenity(requestDto.toEntity(i))) {
                count++;
            }
        }

        return count;

    }

    public ReserveResponseDto selectAmenity(Long id, String username) {

        ReserveResponseDto dto = new ReserveResponseDto();

        Optional<Amenity> amenity = amenityDAO.selectAmenityWhereAdminId(id, username);

        return amenity.map(dto::fromEntity).orElse(null);

    }

    public List<ReserveListResponseDto> selectAmenityList(String username) {

        List<ReserveListResponseDto> dtoList = new ArrayList<>();

        List<Amenity> amenityList = amenityDAO.selectAmenityList(username);

        for(Amenity amenity : amenityList) {
            ReserveListResponseDto dto = new ReserveListResponseDto();
            dtoList.add(dto.fromEntity(amenity));
        }

        return dtoList;

    }

    public boolean updateAmenity(@RequestBody ReserveUpdateRequestDto dto) {
        return amenityDAO.updateAmenity(dto);
    }

    public boolean deleteAmenity(Long id, String username) {
        return amenityDAO.deleteAmenity(id, username);
    }
}
