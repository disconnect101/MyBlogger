package com.dev.web.blogging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, password, active from users where user_id=?"
        );
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/users/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/home").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/admin").hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
        ).formLogin(form ->
                form
                        .loginPage("/users/login")
                        .loginProcessingUrl("/authenticate-user")
                        .permitAll()
        ).logout(logout -> logout.logoutUrl("/logout").permitAll());

        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
