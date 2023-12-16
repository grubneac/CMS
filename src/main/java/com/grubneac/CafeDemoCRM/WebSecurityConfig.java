package com.grubneac.CafeDemoCRM;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.grubneac.CafeDemoCRM.model.Role.ADMIN;
import static com.grubneac.CafeDemoCRM.model.Role.USER;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> {
                    requests
                            .requestMatchers("/", "/home").permitAll()
                            .anyRequest().authenticated();
                })
                .httpBasic(withDefaults())
                .formLogin((form) -> form.loginPage("/login").permitAll())
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/")
                        .permitAll());

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
