package com.example.reservehaja.controller;

import com.example.reservehaja.data.dto.reservationInfoJson.ReservationInfoResponseDto;
import com.example.reservehaja.service.amenity.AmenityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AmenityController {

    private final AmenityService amenityService;

    @PostMapping("/resvOpenApiArray")
    public ReservationInfoResponseDto adminJoinArray(@RequestBody ReservationInfoResponseDto dto) {

        amenityService.addAmenityArray(dto);
        System.out.println(dto.getRESULT().getCODE());

        return dto;
    }

}
