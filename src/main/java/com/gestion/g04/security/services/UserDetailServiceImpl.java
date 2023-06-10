package com.gestion.g04.security.services;

import com.gestion.g04.security.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= accountService.loadUserByUsername(username);
        if(user==null) throw new UsernameNotFoundException(String.format("User %s not found",username));

        String[] roles = user.getRoles().stream().map(u->u.getRole()).toArray(String[]::new);
        UserDetails userDetails= org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .roles(roles)
                .build();

        return userDetails;
    }
}
