package dev.kun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@TableName("user")
public class User implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private String email;
    @JsonIgnore
    private Integer status;
    @JsonIgnore
    private Integer isExpired;
    @JsonIgnore
    private Integer isLocked;
    @JsonIgnore
    private Integer isPasswordExpired;
    @JsonIgnore
    private Integer isEnabled;

    List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isExpired.intValue() == 0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isLocked.intValue() == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isPasswordExpired == 0;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled == 0;
    }
}
