package com.example.reservehaja.service;

import com.example.reservehaja.data.entity.Admin;
import com.example.reservehaja.data.repo.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {

        Admin admin = adminRepository.findByAdminId(adminId).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 계정입니다."));

        return User.builder().username(admin.getAdminId()).password(admin.getAdminPassword()).build();

    }
}
