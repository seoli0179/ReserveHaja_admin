package com.example.reservehaja.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PageController {

    @GetMapping("/")
    public String indexPage() {
        return "/index";
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

    @GetMapping("/amenity")
    public String amenityIndexPage() {
        return "/amenity/index";
    }

    @GetMapping("/amenity/list")
    public String amenityListPage() {
        return "/amenity/amenityListPage";
    }

    @GetMapping("/amenity/create")
    public String amenityCreatePage() {
        return "/amenity/amenityCreatePage";
    }

    @GetMapping("/amenity/select")
    public String amenityReadPage() {
        return "/amenity/amenitySelectPage";
    }

    @GetMapping("/amenity/update")
    public String amenityUpdatePage() {
        return "/amenity/amenityUpdatePage";
    }

    @GetMapping("/amenity/delete")
    public String amenityDeletePage() {
        return "/amenity/amenityDeletePage";
    }

    @GetMapping("/amenity/json")
    public String amenityJsonPage() {
        return "/amenity/amenityJsonPage";
    }

    @GetMapping("/round/list")
    public String roundCListPage() {
        return "/round/roundListPage";
    }

    @GetMapping("/round/create")
    public String roundCreatePage() {
        return "/round/roundCreatePage";
    }

    @GetMapping("/round/read")
    public String roundReadPage() {
        return "/round/roundReadPage";
    }

    @GetMapping("/round/update")
    public String roundUpdatePage() {
        return "/round/roundUpdatePage";
    }

    @GetMapping("/round/delete")
    public String roundDeletePage() {
        return "/round/roundDeletePage";
    }

    @GetMapping("/roundcell/list")
    public String roundCellListPage() {
        return "/roundCell/roundCellListPage";
    }

    @GetMapping("/reserve/list")
    public String reserveListPage() {
        return "/reserve/reserveListPage";
    }

}
