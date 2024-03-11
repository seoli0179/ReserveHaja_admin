package com.example.reservehaja.controller;

import com.example.reservehaja.data.dto.amenity.AmenityListResponseDto;
import com.example.reservehaja.data.dto.amenity.AmenityRequestDto;
import com.example.reservehaja.data.dto.amenity.AmenityResponseDto;
import com.example.reservehaja.data.dto.amenity.AmenityUpdateRequestDto;
import com.example.reservehaja.data.dto.reserveInfoJson.ReserveJsonArrayRequestDto;
import com.example.reservehaja.service.amenity.AmenityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amenity")
@RequiredArgsConstructor
public class AmenityController {

    private final AmenityService amenityService;

    @PostMapping("/jsonArray")
    public int amenityJsonArray(@RequestBody ReserveJsonArrayRequestDto dto) {

        System.out.println(dto.getRESULT().getCODE());
        return amenityService.addAmenityJsonArray(dto);

    }

    @PostMapping("amenity")
    public boolean amenityCreate(@RequestBody AmenityRequestDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                return amenityService.addAmenity(dto, username);
            }
        }
        return false;
    }

    @GetMapping("amenity")
    public AmenityResponseDto amenitySelect(@Param("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // authentication 객체로부터 사용자 정보를 추출합니다.
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                // 사용자의 이름, 권한 등을 여기에서 얻을 수 있습니다.
                String username = userDetails.getUsername();
                // 다른 정보도 사용 가능, 예: userDetails.getAuthorities()

                return amenityService.selectAmenity(id, username);
                // 여기에서 username 또는 다른 사용자 세부 정보를 사용합니다.
                // 예: 데이터베이스 조회, 로그 남기기 등
            }
        }
        return null;
    }

    @PutMapping("amenity")
    public boolean amenityUpdate(@RequestBody AmenityUpdateRequestDto dto) {
        return amenityService.updateAmenity(dto);
    }

    @DeleteMapping("amenity")
    public boolean amenityDelete(@Param("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // authentication 객체로부터 사용자 정보를 추출합니다.
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                // 사용자의 이름, 권한 등을 여기에서 얻을 수 있습니다.
                String username = userDetails.getUsername();
                // 다른 정보도 사용 가능, 예: userDetails.getAuthorities()

                return amenityService.deleteAmenity(id, username);
                // 여기에서 username 또는 다른 사용자 세부 정보를 사용합니다.
                // 예: 데이터베이스 조회, 로그 남기기 등
            }
        }
        return false;
    }

    @GetMapping("amenityList")
    public List<AmenityListResponseDto> amenitySelect() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // authentication 객체로부터 사용자 정보를 추출합니다.
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                // 사용자의 이름, 권한 등을 여기에서 얻을 수 있습니다.
                String username = userDetails.getUsername();
                // 다른 정보도 사용 가능, 예: userDetails.getAuthorities()

                return amenityService.selectAmenityList(username);
                // 여기에서 username 또는 다른 사용자 세부 정보를 사용합니다.
                // 예: 데이터베이스 조회, 로그 남기기 등
            }
        }
        return null;
    }

}
