package com.example.reservehaja.service.amenity;

import com.example.reservehaja.data.dao.admin.AdminDAO;
import com.example.reservehaja.data.dao.amenity.AmenityDAO;
import com.example.reservehaja.data.dto.amenity.AmenityListResponseDto;
import com.example.reservehaja.data.dto.amenity.AmenityRequestDto;
import com.example.reservehaja.data.dto.amenity.AmenityResponseDto;
import com.example.reservehaja.data.dto.amenity.AmenityUpdateRequestDto;
import com.example.reservehaja.data.dto.reserveInfoJson.ReserveJsonArrayRequestDto;
import com.example.reservehaja.data.entity.Admin;
import com.example.reservehaja.data.entity.Amenity;
import com.example.reservehaja.data.repo.AmenityRepository;
import lombok.RequiredArgsConstructor;
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
    private final AmenityRepository amenityRepository;

    public boolean addAmenity(AmenityRequestDto amenityRequestDto, String username) {
        Optional<Admin> admin = adminDAO.findAdmin(username);
        return admin.filter(value -> amenityDAO.addAmenity(amenityRequestDto.toEntity(value))).isPresent();
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

    public AmenityResponseDto selectAmenity(Long id, String username) {

        AmenityResponseDto dto = new AmenityResponseDto();

        Optional<Amenity> amenity = amenityDAO.selectAmenityWhereAdminId(id, username);

        return amenity.map(dto::fromEntity).orElse(null);

    }

    public List<AmenityListResponseDto> selectAmenityList(String username) {

        List<AmenityListResponseDto> dtoList = new ArrayList<>();

        List<Amenity> amenityList = amenityDAO.selectAmenityList(username);

        for(Amenity amenity : amenityList) {
            AmenityListResponseDto dto = new AmenityListResponseDto();
            dtoList.add(dto.fromEntity(amenity));
        }

        return dtoList;

    }

    public boolean updateAmenity(@RequestBody AmenityUpdateRequestDto dto) {
        return amenityDAO.updateAmenity(dto);
    }

    public boolean deleteAmenity(Long id, String username) {
        return amenityDAO.deleteAmenity(id, username);
    }


}
