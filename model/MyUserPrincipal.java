package com.leucine.assignment.model;

import com.leucine.assignment.entity.ApplicationUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserPrincipal implements UserDetails {
    private ApplicationUser user;
    public MyUserPrincipal(ApplicationUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) this.user;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return  this.user.getUsername();
    }
    public String getUserRole() {
        return  this.user.getRole();
    }

    @Override
    public boolean isAccountNonExpired() { return false; }

    @Override
    public boolean isAccountNonLocked() { return false; }

    @Override
    public boolean isCredentialsNonExpired() { return false; }

    @Override
    public boolean isEnabled() { return false; }

}