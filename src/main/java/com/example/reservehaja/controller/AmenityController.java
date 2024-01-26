package com.example.reservehaja.controller;

import com.example.reservehaja.data.dto.reserve.ReserveRequestDto;
import com.example.reservehaja.data.dto.reserve.ReserveResponseDto;
import com.example.reservehaja.data.dto.reserveInfoJson.ReserveJsonArrayRequestDto;
import com.example.reservehaja.service.amenity.AmenityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class AmenityController {

    private final AmenityService amenityService;

    @PostMapping("/resvOpenApiArray")
    public int reserveJsonArray(@RequestBody ReserveJsonArrayRequestDto dto) {

        System.out.println(dto.getRESULT().getCODE());

        return amenityService.addAmenityJsonArray(dto);
    }

    @PostMapping("reserve")
    public boolean reserveCreate(@RequestBody ReserveRequestDto dto) {

        return amenityService.addAmenity(dto);

    }

    @GetMapping("reserve")
    public ReserveResponseDto reserveSelect(@Param("id") Long id) {

        return amenityService.selectAmenity(id);

    }

}
