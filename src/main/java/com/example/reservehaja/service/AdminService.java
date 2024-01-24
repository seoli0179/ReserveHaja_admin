package com.example.reservehaja.service;

import com.example.reservehaja.data.dto.AdminJoinRequestDto;
import com.example.reservehaja.data.repo.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public void addUser(AdminJoinRequestDto requestDto) {
        if(adminRepository.findByAdminId(requestDto.getAdminId()).isEmpty())
            adminRepository.save(requestDto.toEntity(passwordEncoder));
    }

}
