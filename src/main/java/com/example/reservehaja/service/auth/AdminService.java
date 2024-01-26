package com.example.reservehaja.service.auth;

import com.example.reservehaja.data.dao.admin.AdminDAO;
import com.example.reservehaja.data.dto.auth.AdminJoinRequestDto;
import com.example.reservehaja.data.repo.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminDAO adminDAO;

    public boolean addUser(AdminJoinRequestDto requestDto) {

        return adminDAO.addAdmin(requestDto);

    }

}
