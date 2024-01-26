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
/* 순환참조에러 발생 securityConfig -> customAuthenticationSuccessHandler -> adminService -> adminDAO // PasswordEncoder 때문인것으로 예상
    public String selectAdminName(String adminId) {
        return adminDAO.selectAdminName(adminId);
    }

 */

}
