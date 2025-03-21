package com.ksj.bootstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf((crsfConfig) -> crsfConfig.disable())
            .headers((headerConfig) -> headerConfig.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()))
            .authorizeHttpRequests((authoziedRequest) -> authoziedRequest
                    .requestMatchers("/", "/img/**").permitAll()
                    .requestMatchers("/login/**").permitAll()
                    .requestMatchers("/h2-console/**").permitAll()
                    .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                    .requestMatchers("/menu/**").hasAnyRole("ADMIN")
                    .requestMatchers("/role/**").hasAnyRole("ADMIN")
                    .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/bbs/create_bbs.page/**").authenticated()
                    .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin
                    .loginPage("/login/member_login.page")
                    .loginProcessingUrl("/login/member_login")
                    .usernameParameter("id")
                    .passwordParameter("passwd")
                    .defaultSuccessUrl("/")
                    .permitAll()
            )
            .logout(logout -> logout
                    .logoutUrl("/login/member_logout.do")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
