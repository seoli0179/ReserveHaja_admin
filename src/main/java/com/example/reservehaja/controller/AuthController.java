package com.example.reservehaja.controller;

import com.example.reservehaja.data.dto.auth.AdminJoinRequestDto;
import com.example.reservehaja.service.auth.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AdminService adminService;

    @PostMapping("/join")
    public boolean adminJoin(@RequestBody AdminJoinRequestDto requestDto) {
        return adminService.addUser(requestDto);
    }

    @PostMapping("/joinArray")
    public String adminJoinArray(@RequestBody List<AdminJoinRequestDto> list) {

        for(AdminJoinRequestDto dto : list){
            adminService.addUser(dto);
        }

        return "SUCCESS";
    }

}
