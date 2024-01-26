package com.example.reservehaja.data.dto.auth;

import com.example.reservehaja.data.entity.Admin;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
public class AdminJoinRequestDto {

    private String adminId;
    private String adminPassword;
    private String adminName;
    private String adminPhone;
    private String adminZip;
    private String adminEmail;

    public Admin toEntity(PasswordEncoder passwordEncoder){

        Admin admin = new Admin();
        admin.setAdminId(adminId);
        admin.setAdminPassword(passwordEncoder.encode(adminPassword));
        admin.setAdminName(adminName);
        admin.setAdminPhone(adminPhone);
        admin.setAdminZip(adminZip);
        admin.setAdminEmail(adminEmail);

        return admin;
    }

}
