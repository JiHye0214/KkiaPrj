package com.project.kkiaprj.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import java.io.IOException;

public class LogoutSuccess implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        System.out.println("### Logout Success : CustomLogoutSuccessHandler working ###");
        request.getSession().removeAttribute("isLogin");

        HttpSession session = request.getSession();

        if(session != null) {
            String redirectUrl = (String) session.getAttribute("prevPage");
            if(redirectUrl != null) {
                session.removeAttribute("prevPage");
                redirectStrategy.sendRedirect(request, response, redirectUrl);
            } else {
                redirectStrategy.sendRedirect(request, response, "/");
            }
        }
    }
}
