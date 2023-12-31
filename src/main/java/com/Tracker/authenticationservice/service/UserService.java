package com.Tracker.authenticationservice.service;


import com.Tracker.authenticationservice.dto.UserRegistrationRequest;
import com.Tracker.authenticationservice.model.Role;
import com.Tracker.authenticationservice.model.User;
import com.Tracker.authenticationservice.repository.RoleRepository;
import com.Tracker.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser()
    {
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin Role: Top Priority");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("User Role: Restricted Priority");
        roleRepository.save(userRole);

        User adminUser = new User();
        adminUser.setName("administrator1");
        adminUser.setEmail("admin@gmail.com");
        adminUser.setUsername("admin");
        adminUser.setUserPassword(getEncodedPassword("password"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepository.save(adminUser);
    }

//    public User registerNewUser(UserRegistrationRequest userRegistrationRequest)
//    {
//        User user = new User();
//        user.setUsername(userRegistrationRequest.getUsername());
//        user.setEmail(userRegistrationRequest.getEmail());
//        user.setName(userRegistrationRequest.getName());
//        user.setUserPassword(getEncodedPassword(userRegistrationRequest.getUserPassword()));
//
//        Role userRole = roleRepository.findById("User").orElseThrow(() -> new RuntimeException("User role not found"));
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//
//        return userRepository.save(user);
//    }
public User registerNewUser(UserRegistrationRequest userRegistrationRequest)
{
    User user = userRepository.findByUsername(userRegistrationRequest.getUsername());
    if (user != null)
    {
        throw new RuntimeException("Username already exists");
    }

    user = new User();
    user.setUsername(userRegistrationRequest.getUsername());
    user.setEmail(userRegistrationRequest.getEmail());
    user.setName(userRegistrationRequest.getName());
    user.setUserPassword(getEncodedPassword(userRegistrationRequest.getUserPassword()));

    Role userRole = roleRepository.findById("User").orElseThrow(() -> new RuntimeException("User role not found"));
    Set<Role> userRoles = new HashSet<>();
    userRoles.add(userRole);
    user.setRole(userRoles);

    return userRepository.save(user);
}
    private String getEncodedPassword(String password)
    {
        return passwordEncoder.encode(password);
    }
}
