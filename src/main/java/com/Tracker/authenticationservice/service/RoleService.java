package com.Tracker.authenticationservice.service;

import com.Tracker.authenticationservice.model.Role;
import com.Tracker.authenticationservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createNewRole(Role role)
    {
        return roleRepository.save(role);
    }
}
