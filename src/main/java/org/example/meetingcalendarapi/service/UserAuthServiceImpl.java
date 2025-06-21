package org.example.meetingcalendarapi.service;

import jakarta.transaction.Transactional;
import org.example.meetingcalendarapi.dto.UserDto;
import org.example.meetingcalendarapi.dto.UserRegisterDto;
import org.example.meetingcalendarapi.mapper.UserMapper;
import org.example.meetingcalendarapi.mapper.UserRegisterMapper;
import org.example.meetingcalendarapi.model.User;
import org.example.meetingcalendarapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthServiceImpl implements UserAuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserRegisterMapper userRegisterMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAuthServiceImpl(UserRepository userRepository, UserMapper userMapper, UserRegisterMapper userRegisterMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userRegisterMapper = userRegisterMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            return userMapper.toDto(existingUser);
        }
        
        return null;
    }

    @Transactional
    @Override
    public UserDto registerUser(UserRegisterDto userRegisterDto) {
        User newUser = userRegisterMapper.toEntity(userRegisterDto);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        
        return userMapper.toDto(newUser);
    }
}
