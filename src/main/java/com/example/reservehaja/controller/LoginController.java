package com.example.reservehaja.controller;

import com.example.reservehaja.data.dto.AdminJoinRequestDto;
import com.example.reservehaja.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final AdminService adminService;

    @GetMapping("/")
    public String indexPage(){
        return "login";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/join")
    public String joinPage(){
        return "join";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }

    @PostMapping("/join")
    public String userJoin(@ModelAttribute AdminJoinRequestDto requestDto) {
        adminService.addUser(requestDto);
        return "login";
    }

}
