package org.example.meetingcalendarapi.service;

import jakarta.transaction.Transactional;
import org.example.meetingcalendarapi.dto.UserDto;
import org.example.meetingcalendarapi.dto.UserRegisterDto;
import org.example.meetingcalendarapi.mapper.ProfileMapper;
import org.example.meetingcalendarapi.mapper.UserMapper;
import org.example.meetingcalendarapi.mapper.UserRegisterMapper;
import org.example.meetingcalendarapi.model.Profile;
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
    private final ProfileMapper profileMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAuthServiceImpl(UserRepository userRepository, UserMapper userMapper,
                               UserRegisterMapper userRegisterMapper, ProfileMapper profileMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userRegisterMapper = userRegisterMapper;
        this.profileMapper = profileMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsername(username).orElse(null));
    }

    @Transactional
    @Override
    public UserDto registerUser(UserRegisterDto userRegisterDto) {
        User newUser = userRegisterMapper.toEntity(userRegisterDto);
        Profile newProfile = profileMapper.toEntity(userRegisterDto.getProfile());
        newProfile.setUser(newUser);
        newUser.setProfile(newProfile);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        
        return userMapper.toDto(newUser);
    }
}
