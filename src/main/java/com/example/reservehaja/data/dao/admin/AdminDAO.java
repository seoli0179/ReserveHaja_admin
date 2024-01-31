package com.example.reservehaja.data.dao.admin;

import com.example.reservehaja.data.dto.auth.AdminJoinRequestDto;
import com.example.reservehaja.data.entity.Admin;
import com.example.reservehaja.data.repo.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdminDAO {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    public boolean isEmptyAdmin(String adminId) {
        return adminRepository.findByAdminId(adminId).isEmpty();
    }

    public Optional<Admin> findAdmin(String adminId) {
        return adminRepository.findByAdminId(adminId);
    }

    public boolean addAdmin(AdminJoinRequestDto adminJoinRequestDto) {

        if (isEmptyAdmin(adminJoinRequestDto.getAdminId())) {
            adminRepository.save(adminJoinRequestDto.toEntity(passwordEncoder));
            return true;
        }

        return false;

    }

}
