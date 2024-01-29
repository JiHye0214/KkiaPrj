package com.project.kkiaprj.config;

import jakarta.security.auth.message.callback.PrivateKeyCallback;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;

public class LoginSuccess extends SavedRequestAwareAuthenticationSuccessHandler {

    public LoginSuccess(String defaultTargetUrl){
        setDefaultTargetUrl(defaultTargetUrl);
    }
    RequestCache requestCache = new HttpSessionRequestCache();
    RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("isLogin", authentication.isAuthenticated());

        // 로그인 필수인 페이지만 저장하는 친구
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if(savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request, response, redirectUrl);
        } else {
            String redirectUrl = (String) session.getAttribute("prevPage");
            if(redirectUrl != null) {
                session.removeAttribute("prevPage");
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
            } else {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        }
    }
}
