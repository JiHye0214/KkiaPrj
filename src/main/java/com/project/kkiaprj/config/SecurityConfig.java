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
//                        .requestMatchers("/community/post/detail/*").authenticated()
//                        .requestMatchers("/community/post/update/*").authenticated()
//                        .requestMatchers("/community/post/write").authenticated()
//                        .requestMatchers("/community/market/detail/*").authenticated()
//                        .requestMatchers("/community/market/update/*").authenticated()
//                        .requestMatchers("/community/market/write").authenticated()
                        .requestMatchers("/community/favorite/detail/*").authenticated()
                        .requestMatchers("/community/favorite/update/*").authenticated()
                        .requestMatchers("/community/favorite/write").authenticated()
                        .requestMatchers("/community/food/detail/*").authenticated()
                        .requestMatchers("/community/food/update/*").authenticated()
                        .requestMatchers("/community/food/write").authenticated()
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
