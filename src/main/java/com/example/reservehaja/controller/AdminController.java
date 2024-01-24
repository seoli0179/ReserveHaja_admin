package com.example.reservehaja.controller;

import com.example.reservehaja.data.dao.dto.ReservationInfo.ReservationInfoResponseDto;
import com.example.reservehaja.service.AmenityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AmenityService amenityService;

    @PostMapping("/resvOpenApiArray")
    public ReservationInfoResponseDto adminJoinArray(@RequestBody ReservationInfoResponseDto dto) {

        amenityService.addAmenityArray(dto);
        System.out.println(dto.getRESULT().getCODE());

        return dto;
    }

}
