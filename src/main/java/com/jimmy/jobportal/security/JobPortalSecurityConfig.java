package com.jimmy.jobportal.security;

import com.jimmy.jobportal.security.filter.JwtTokenValidatorFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class JobPortalSecurityConfig {

    private final List<String> publicPaths;
    private final List<String> securedPaths;

    public JobPortalSecurityConfig(
            @Qualifier("publicPaths") List<String> publicPaths,
            @Qualifier("securedPaths") List<String> securedPaths) {

        this.publicPaths = publicPaths;
        this.securedPaths = securedPaths;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        http.authorizeHttpRequests(request -> {
            publicPaths.forEach(path -> request.requestMatchers(path).permitAll());
            securedPaths.forEach(path -> request.requestMatchers(path).authenticated());
            request.anyRequest().denyAll();
        }).csrf(csrfConfig->csrfConfig.disable())
                .addFilterBefore(new JwtTokenValidatorFilter(publicPaths), BasicAuthenticationFilter.class)
                .formLogin(formLoginConfig->formLoginConfig.disable())
                .httpBasic(Customizer.withDefaults());

        return http.build();

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user1 = User.builder().username("jimmy")
                .password("$2a$12$tLThFjYlhjsgfvTHxjhqvexRtCGbr/9y1.iGXSHw.Q9bFfXfkUxnK")
                .roles("USER").build();
        var user2 = User.builder().username("admin")
                .password("$2a$12$vdvKOn9PFLaf7FeEXKhhLeIyc1TslTxLpqrJxp5xBs4guYXN35hqm")
                .roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1, user2);
    }


    @Bean
    public AuthenticationManager authenticationManager() {
        var authenticationProvider = new DaoAuthenticationProvider(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
