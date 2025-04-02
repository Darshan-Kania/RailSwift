package com.RailSwift.Devlopment.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class MainSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
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
//                        User
                                .requestMatchers(HttpMethod.POST, "/RailSwift/User/UserDetails/MultipleNewUser").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.POST, "/RailSwift/User/UserDetails/NewUser").permitAll()
                                .requestMatchers(HttpMethod.GET, "/RailSwift/User/UserDetails/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/RailSwift/User/UserDetails/UpdateRole/{userId}/{role}").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/RailSwift/User/UserDetails/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/RailSwift/User/UserDetails/{userId}").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
//                        Train
                                .requestMatchers(HttpMethod.GET, "RailSwift/Train/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "RailSwift/Train/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT, "RailSwift/Train/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "RailSwift/Train/**").hasAuthority("ROLE_ADMIN")
//                       Station
                                .requestMatchers(HttpMethod.GET, "RailSwift/Station/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "RailSwift/Station/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT, "RailSwift/Station/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "RailSwift/Station/**").hasAuthority("ROLE_ADMIN")
        );
        http.csrf().disable();
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }


}
