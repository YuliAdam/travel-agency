package org.example.config;

import org.example.entity.Admin;
import org.example.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.templateresolver.DefaultTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolution;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.userDetailsService(customUserDetailsService)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers("/user/redirect/create","/user/create").permitAll()
                        .requestMatchers("/user/search").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/menu"))
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.permitAll()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
