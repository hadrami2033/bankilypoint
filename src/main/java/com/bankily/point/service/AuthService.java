package com.bankily.point.service;

import java.util.List;

import com.bankily.point.entity.User;
import com.bankily.point.payload.LoginDto;
import com.bankily.point.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
    
    List<User> allUsers();

}
