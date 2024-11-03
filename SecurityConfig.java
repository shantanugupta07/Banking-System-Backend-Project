package com.example.BankingSystemProject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for testing purposes; enable in production
                .authorizeRequests()
                .anyRequest().permitAll()  // Allow access to all endpoints
                .and()
                .httpBasic(withDefaults());  // Use basic authentication

        return http.build();


    }

     */

    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails admin= User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();

        UserDetails customer= User.withDefaultPasswordEncoder()
                .username("customer")
                .password("customer123")
                .roles("CUSTOMER")
                .build();

        UserDetails teller= User.withDefaultPasswordEncoder()
                .username("teller")
                .password("teller123")
                .roles("TELLER")
                .build();

        return new InMemoryUserDetailsManager(admin, customer, teller);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable())
                .authorizeRequests()
                .requestMatchers("/transactions/statements").hasAnyRole("ADMIN", "CUSTOMER")
                .requestMatchers("/transactions/deposit", "/transactions/withdrawal").hasAnyRole("ADMIN", "TELLER", "CUSTOMER")
                .requestMatchers("/transactions/transfer").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic(withDefaults());

        return http.build();
    }


}