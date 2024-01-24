package com.example.reservehaja.service;

import com.example.reservehaja.data.dao.dto.AdminJoinRequestDto;
import com.example.reservehaja.data.repo.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public String addUser(AdminJoinRequestDto requestDto) {

        if(adminRepository.findByAdminId(requestDto.getAdminId()).isEmpty()){
            adminRepository.save(requestDto.toEntity(passwordEncoder));
            return "SUCCESS";
        }else{
            return "FAIL";
        }

    }

}
