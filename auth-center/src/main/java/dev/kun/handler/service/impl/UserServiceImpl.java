package dev.kun.handler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.kun.entity.Role;
import dev.kun.entity.User;
import dev.kun.handler.mapper.RoleMapper;
import dev.kun.handler.mapper.UserMapper;
import dev.kun.handler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public RoleMapper roleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByUsername = userMapper.findUserByUsername(username);
        List<Role> roles = roleMapper.findRoleByUserId(userByUsername.getId());
        userByUsername.setRoles(roles);
        return userByUsername;
    }


}
