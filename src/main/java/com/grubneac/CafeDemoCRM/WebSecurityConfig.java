package com.grubneac.CafeDemoCRM;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.grubneac.CafeDemoCRM.model.Permission.*;
import static com.grubneac.CafeDemoCRM.model.Role.ADMIN;
import static com.grubneac.CafeDemoCRM.model.Role.USER;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> {
                    requests
                            .requestMatchers("/", "/home").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/**").hasAuthority(PERSON_READ.getPermission())
                            .requestMatchers(HttpMethod.POST, "/api/**").hasAuthority(PERSON_WRITE.getPermission())
                            .requestMatchers(HttpMethod.PUT, "/api/**").hasAuthority(PERSON_UPDATE.getPermission())
                            .requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(PERSON_DELETE.getPermission())
                            .anyRequest().authenticated();
                })
                .httpBasic(withDefaults())
                .formLogin((form) -> form.loginPage("/login").permitAll())
                .logout((logout) -> logout.logoutUrl("/logout").permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("user")
                        .authorities(USER.getAuthoritiesSet())
                        .build();
        var admin =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin")
                        .authorities(ADMIN.getAuthoritiesSet())
                        .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
