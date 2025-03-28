package com.RailSwift.Devlopment.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class MainSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select email, password, 1 as enabled from users where email=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select email, CONCAT('ROLE_', role) from users where email=?"
        );


        return jdbcUserDetailsManager;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.POST, "/RailSwift/User/UserDetails/MultipleNewUser").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/RailSwift/User/UserDetails/NewUser").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers(HttpMethod.GET, "/RailSwift/User/UserDetails/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER") // ✅ Ensure path is correct
                        .requestMatchers(HttpMethod.PUT, "/RailSwift/User/UserDetails/UpdateRole/{userId}/{role}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/RailSwift/User/UserDetails/**").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.DELETE, "/RailSwift/User/UserDetails/{userId}").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
        );

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable()); // ✅ Disable CSRF for APIs

        return http.build();
    }

}
