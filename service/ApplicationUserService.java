package com.leucine.assignment.service;

import com.leucine.assignment.entity.ApplicationUser;
import com.leucine.assignment.exception.ResourceNotFoundExeception;
import com.leucine.assignment.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationUserService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public ApplicationUser createUser(ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return applicationUserRepository.save(user);
    }

    public List<ApplicationUser> getAllUsers() {
        return applicationUserRepository.findAll();
    }

    public ApplicationUser getUserById(Long id) {
        return applicationUserRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
        applicationUserRepository.deleteById(id);
    }

    public ApplicationUser updateUser(Long id, ApplicationUser user) {
        if (applicationUserRepository.existsById(id)) {
            user.setId(id);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return applicationUserRepository.save(user);
        }
        return null;
    }

    public ApplicationUser getUserByUsername(String username) {
        return applicationUserRepository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundExeception("User does not exists with username :" + username));
    }

    public String getUsernameById(Long id) {
        ApplicationUser user = applicationUserRepository.findById(id).orElse(null);
        return (user != null) ? user.getUsername() : null;
    }
}
