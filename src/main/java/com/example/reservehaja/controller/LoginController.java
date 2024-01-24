package com.example.reservehaja.controller;

import com.example.reservehaja.data.dao.dto.AdminJoinRequestDto;
import com.example.reservehaja.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AdminService adminService;

    @PostMapping("/join")
    public String adminJoin(AdminJoinRequestDto requestDto) {
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
