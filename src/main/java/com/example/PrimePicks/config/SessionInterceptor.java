package com.example.PrimePicks.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();


        if (uri.equals("/") || uri.equals("/createUser") || uri.equals("/RegisterPage") || uri.equals("/LoginPage") ||
                uri.startsWith("/css") || uri.startsWith("/js") || uri.startsWith("/images") ||
                uri.startsWith("/aboutPage") || uri.startsWith("/category") || uri.startsWith("/ViewProduct")) {
            return true;
        }


        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("/LoginPage");
            return false;
        }
        return true;
    }

}
