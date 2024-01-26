package com.example.reservehaja.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        // 로그인 실패에 대한 정보를 처리
        String errorMessage = "Invalid Username or Password";

        // JSON 형식으로 응답하는 경우
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + errorMessage + "\"}");

        // 또는 페이지로 리다이렉트하는 경우
        // super.setDefaultFailureUrl("/login?error=true&message=" + errorMessage);
        // super.onAuthenticationFailure(request, response, exception);
    }
}