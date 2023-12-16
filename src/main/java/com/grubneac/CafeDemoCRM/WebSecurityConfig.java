package com.grubneac.CafeDemoCRM;

import com.grubneac.CafeDemoCRM.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
                                    .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                                    .requestMatchers(HttpMethod.POST, "/api/**").hasAnyRole(Role.ADMIN.name())
                                    .requestMatchers(HttpMethod.PUT, "/api/**").hasAnyRole(Role.ADMIN.name())
                                    .requestMatchers(HttpMethod.DELETE, "/api/**").hasAnyRole(Role.ADMIN.name())
                                    .anyRequest().authenticated();
                        }
                )
                .httpBasic(withDefaults())
                .formLogin((form) -> form.loginPage("/login").permitAll())
                .logout((logout) -> logout.logoutUrl("/logout").permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("user")
                        .roles(Role.USER.name())
                        .build();
        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin")
                        .roles(Role.ADMIN.name())
                        .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
