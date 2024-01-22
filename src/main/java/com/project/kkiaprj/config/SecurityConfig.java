package com.project.kkiaprj.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    // password Encoder
    @Bean
    public
    PasswordEncoder encoder(){
        System.out.println("PasswordEncoder bean is created");
        return new BCryptPasswordEncoder();
    }

    // security setting
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화

                // authority setting
                .authorizeHttpRequests(auth -> auth
                        // 로그인만 하면 들어갈 수 있도록 !
                        .requestMatchers("/post/detail/*").authenticated()
                        .requestMatchers("/post/write").authenticated()
                        .requestMatchers("/market/detail/*").authenticated()
                        .requestMatchers("/market/write").authenticated()
                        .requestMatchers("/favorite/detail/*").authenticated()
                        .requestMatchers("/favorite/write").authenticated()
                        .requestMatchers("/food/detail/*").authenticated()
                        .requestMatchers("/food/write").authenticated()
                        .requestMatchers("/user/mypage").authenticated()
                        .anyRequest().permitAll()
                )

                .formLogin(form -> form
                        .usernameParameter("loginId")
                        .loginPage("/user/logIn")
                        .loginProcessingUrl("/user/logIn")
                        .successHandler(new LoginSuccess("/home"))
                        .failureHandler(new LoginFailure())
                )

                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutUrl("/doLogout")
                        .logoutSuccessUrl("/home")
                        .invalidateHttpSession(false)
                )

                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
                        .accessDeniedHandler(new AccessDeniedHandler1())
                )

                .build();
    }
}
