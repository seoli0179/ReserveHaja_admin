package com.example.reservehaja.data.dto;

import com.example.reservehaja.data.entity.Admin;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
public class AdminJoinRequestDto {

    private String adminId;
    private String adminPassword;

    public Admin toEntity(PasswordEncoder passwordEncoder){

        Admin admin = new Admin();
        admin.setAdminId(adminId);
        admin.setAdminPassword(passwordEncoder.encode(adminPassword));

        return admin;
    }

}
