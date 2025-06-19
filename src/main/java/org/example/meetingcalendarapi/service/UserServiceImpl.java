package org.example.meetingcalendarapi.service;

import org.example.meetingcalendarapi.dto.UserDto;
import org.example.meetingcalendarapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getById(Long id) {
        return null;
    }

    @Override
    public List<UserDto> getAll() {
        return List.of();
    }
}
