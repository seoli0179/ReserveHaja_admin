package com.example.reservehaja.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PageController {

    @GetMapping("/")
    public String indexPage() {
        return "/auth/login";
    }

    @GetMapping("/auth/login")
    public String loginPage() {
        return "/auth/login";
    }

    @GetMapping("/auth/join")
    public String joinPage() {
        return "/auth/join";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "/admin/admin";
    }

    @GetMapping("/reserve")
    public String reserveIndexPage(){
        return "/reserve/index";
    }

    @GetMapping("/reserve/create")
    public String reserveCreatePage(){
        return "/reserve/reserveCreatePage";
    }

    @GetMapping("/reserve/select")
    public String reserveSelectPage(){
        return "/reserve/reserveSelectPage";
    }

}
