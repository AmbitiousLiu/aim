package com.example.web.service;

import com.example.web.DO.User;
import com.example.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author jleo
 * @date 2021/2/16
 */
@Service
public class LoginService {
    @Autowired
    private UserMapper userMapper;

    public boolean doLogin(String userName, String password) {
        User user = userMapper.selectById(userName);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    public String getUserName(String userId) {
        User user = userMapper.selectById(userId);
        return Optional.ofNullable(user).map(User::getName).orElse(null);
    }
}
