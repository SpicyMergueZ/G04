package com.gestion.g04.security.services;

import com.gestion.g04.security.entities.Role;
import com.gestion.g04.security.entities.User;
import org.springframework.stereotype.Service;

//@Service
public interface AccountService{


    User createUser(String username, String password, String email, String confirmPassword);

    Role createRole(String role);

    void addRoleToUser(String username, String role);

    void removeRolefromUser(String username, String role);

    User loadUserByUsername (String username);


}
