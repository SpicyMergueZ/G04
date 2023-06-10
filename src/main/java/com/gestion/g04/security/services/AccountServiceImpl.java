package com.gestion.g04.security.services;

import com.gestion.g04.security.entities.Role;
import com.gestion.g04.security.entities.User;
import com.gestion.g04.security.repositories.RoleRepository;
import com.gestion.g04.security.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService{

    /*
    @Autowired
     RoleRepository roleRepository;
     @Autowired
     UserRepository userRepository;
    */
    //Injection de dépendance par constructeur (sans autowired)
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(String username, String password, String email, String confirmPassword) {
        /* VARIANTE 1
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        return userRepository.save(user);

         */
        //VARIANTE 2
        User user = userRepository.findByUsername(username);
        if (user != null) throw new RuntimeException("USERNAME ALREADY EXIST");
        if (!password.equals(confirmPassword)) throw new RuntimeException("PASSWORD NOT CONFIRMED");
        user = User.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        return userRepository.save(user);
    }

    @Override
    public Role createRole(String role) {

        Role role1 = roleRepository.findById(role).orElse(null);


        role1 = Role.builder()
                .role(role)
                .build();

        return roleRepository.save(role1);
    }

    @Override
    public void addRoleToUser(String username, String role) {

        User user = userRepository.findByUsername(username);
        Role role1 = roleRepository.findById(role).orElse(null);

        user.getRoles().add(role1);

    }

    @Override
    public void removeRolefromUser(String username, String role) {

        User user = userRepository.findByUsername(username);
        Role role1 = roleRepository.findById(role).orElse(null);

        user.getRoles().remove(role1);

    }

    @Override
    public User loadUserByUsername(String username) {



        return userRepository.findByUsername(username);
    }
}
