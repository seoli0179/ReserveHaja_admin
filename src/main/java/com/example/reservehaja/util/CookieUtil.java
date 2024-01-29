package com.example.reservehaja.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static void createCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/"); // 쿠키가 전송될 경로 설정, '/'는 모든 경로에 적용됩니다.
        cookie.setMaxAge(maxAgeInSeconds); // 쿠키의 유효 기간 설정(초 단위)
        cookie.setHttpOnly(false); // JavaScript를 통해 쿠키에 접근할 수 없도록 설정
        response.addCookie(cookie); // 응답에 쿠키 추가
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0); // 쿠키의 유효기간을 0으로 설정하여 즉시 제거하도록 합니다.
                    response.addCookie(cookie);
                }
            }
        }
    }

}