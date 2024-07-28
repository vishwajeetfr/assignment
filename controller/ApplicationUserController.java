package com.leucine.assignment.controller;

import com.leucine.assignment.entity.ApplicationUser;
import com.leucine.assignment.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class ApplicationUserController {

    @Autowired
    private ApplicationUserService applicationUserService;

    @GetMapping("/{id}")
    public ApplicationUser getUserById(@PathVariable Long id) {
        return applicationUserService.getUserById(id);
    }

    @GetMapping("/username/{username}")
    public ApplicationUser getUserByUserName(@PathVariable String username) {
        return applicationUserService.getUserByUserName(username);
    }

    @GetMapping
    public List<ApplicationUser> getAllUsers() {
        return applicationUserService.getAllUsers();
    }

    @PostMapping
    public ApplicationUser createUser(@RequestBody ApplicationUser user) {
        return applicationUserService.createUser(user);
    }

    @PutMapping("/{id}")
    public ApplicationUser updateUser(@PathVariable Long id, @RequestBody ApplicationUser user) {
        return applicationUserService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        applicationUserService.deleteUser(id);
    }
}
