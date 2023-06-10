package com.gestion.g04.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.net.http.HttpRequest;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll();
        httpSecurity.authorizeHttpRequests((requests) -> requests.requestMatchers("/webjars/**").permitAll());

        httpSecurity.authorizeHttpRequests((requests) -> requests.requestMatchers("/createProduct", "/saveProduct").hasAnyAuthority("ROLE_ADMIN", "ROLE_CASHIER"));
        httpSecurity.authorizeHttpRequests((requests) -> requests.requestMatchers("/showProduct", "/updateProduct", "/deleteProduct").hasAnyAuthority("ROLE_ADMIN"));
        httpSecurity.authorizeHttpRequests((requests) -> requests.requestMatchers("/productsList").hasAnyAuthority("ROLE_ADMIN", "ROLE_CASHIER", "ROLE_USER"));
        httpSecurity.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());
        httpSecurity.exceptionHandling().accessDeniedPage("/accessDenied");
        return httpSecurity.build();
    }

    //@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").password(passwordEncoder.encode("123")).roles("ADMIN", "USER").build(),
                User.withUsername("cashier").password(passwordEncoder.encode("123")).roles("CASHIER").build(),
                User.withUsername("accountant").password(passwordEncoder.encode("123")).roles("USER").build()

        );
    }

    //@Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }


}

