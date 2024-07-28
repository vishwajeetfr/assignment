package com.leucine.assignment.controller;

import com.leucine.assignment.config.CustomUserDetailService;
import com.leucine.assignment.config.JwtHelper;
import com.leucine.assignment.model.JwtRequest;
import com.leucine.assignment.model.JwtResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getUserName(), request.getPassword());


        final var userDetails = userDetailsService.loadUserByUsername(request.getUserName());

        boolean isRoleMatched = userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(request.getRole()));
        if(!isRoleMatched){
            throw new RuntimeException(request.getUserName() + "does not have access ROLE : " + request.getRole());
        }

        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .role(String.valueOf(userDetails.getAuthorities().stream().findFirst().get()))
                .userName(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password) {


        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);

        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}

