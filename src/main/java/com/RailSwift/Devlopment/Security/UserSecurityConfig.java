package com.RailSwift.Devlopment.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // ✅ Ensure security is enabled
public class UserSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username("Admin")
                .password(passwordEncoder.encode("Admin123")) // Use encrypted password
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("User")
                .password(passwordEncoder.encode("User123")) // Use encrypted password
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth.anyRequest().authenticated() // ✅ Require authentication for all requests
        );

        http.httpBasic(); // ✅ Enable HTTP Basic Authentication
        http.csrf(csrf -> csrf.disable()); // ✅ Disable CSRF for APIs

        return http.build();
    }
}
