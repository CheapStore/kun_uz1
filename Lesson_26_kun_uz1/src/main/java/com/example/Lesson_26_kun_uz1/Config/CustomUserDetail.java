package com.example.Lesson_26_kun_uz1.Config;

import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Enums.ProfileStatus;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.LinkedList;
import java.util.List;



public class CustomUserDetail implements UserDetails {



    @Getter
    private Integer id;
    private String email;
    private String password;
    private ProfileStatus status;
    @Getter
    private ProfileRole role;

    public CustomUserDetail(Integer id, String email, String password, ProfileStatus status, ProfileRole role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new LinkedList<>();
        list.add(new SimpleGrantedAuthority(role.name()));
        return list;

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status.equals(ProfileStatus.ACTIVE);
    }

}
