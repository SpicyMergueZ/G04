package com.gestion.g04;

import com.gestion.g04.security.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
public class G04Application {

    public static void main(String[] args) {
        SpringApplication.run(G04Application.class, args);
    }


    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
        PasswordEncoder passwordEncoder = passwordEncoder();

        return args -> {

            jdbcUserDetailsManager.createUser(User.withUsername("admin").password(passwordEncoder.encode("123")).roles("ADMIN", "USER").build());
            jdbcUserDetailsManager.createUser(User.withUsername("cashier").password(passwordEncoder.encode("123")).roles("CASHIER").build());
            jdbcUserDetailsManager.createUser(User.withUsername("accountant").password(passwordEncoder.encode("123")).roles("USER").build());

            System.out.println("account create");

        };


    }


    @Bean
    CommandLineRunner commandLineRunnerUser(AccountService accountService) {


        return args -> {

			accountService.createUser("admin","123","admin@business.com","123");
			accountService.createUser("cashier","123","cashier@business.com","123");
			accountService.createUser("accountant","123","cashier@business.com","123");

			accountService.createRole("ADMIN");
			accountService.createRole("CASHIER");
			accountService.createRole("USER");

			accountService.addRoleToUser("admin", "ADMIN");
			accountService.addRoleToUser("admin", "USER");
			accountService.addRoleToUser("cashier", "CASHIER");
			accountService.addRoleToUser("accountant", "USER");


            //System.out.println(accountService.loadUserByUsername("cashier").getEmail());


        };


    }



}

