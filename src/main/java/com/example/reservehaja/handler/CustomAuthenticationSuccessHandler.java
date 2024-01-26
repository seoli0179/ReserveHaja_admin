package com.example.reservehaja.handler;

import com.example.reservehaja.data.entity.Admin;
import com.example.reservehaja.data.repo.AdminRepository;
import com.example.reservehaja.service.auth.AdminService;
import com.example.reservehaja.util.CookieUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final AdminRepository adminRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        if (authentication != null && authentication.isAuthenticated()) {
            // authentication 객체로부터 사용자 정보를 추출합니다.
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails userDetails) {

                // UserDetails userDetails = (UserDetails) principal;

                Optional<Admin> admin = adminRepository.findByAdminId(userDetails.getUsername());

                if(admin.isPresent()){
                    String adminName = admin.get().getAdminName();
                    CookieUtil.createCookie(response, "userId", admin.get().getAdminId(), 3600);
                    CookieUtil.createCookie(response, "username", adminName, 3600);
                }

            }
        }


        // 기본 성공 URL로 리디렉션 (또는 다른 로직에 따라 결정)
        super.onAuthenticationSuccess(request, response, authentication);
    }
}