package org.example.meetingcalendarapi.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.meetingcalendarapi.dto.UserDto;
import org.example.meetingcalendarapi.enums.UserRole;
import org.example.meetingcalendarapi.mapper.UserMapper;
import org.example.meetingcalendarapi.model.User;
import org.example.meetingcalendarapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public UserDto getByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsername(username).orElse(null));
    }

    @Override
    public List<UserDto> getAll() {
        return userMapper.toDtoList((List<User>) userRepository.findAll());
    }

    @Override
    public List<UserDto> getAllSearchable() {
        return userMapper.toDtoList(userRepository.findBySearchEnabledTrue());
    }

    @Override
    public List<UserDto> getAllById(List<Long> ids) {
        return userMapper.toDtoList((List<User>) userRepository.findAllById(ids));
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            throw new EntityNotFoundException("User not found");
        }
        
        userMapper.updateEntity(userDto, existingUser);
        return userMapper.toDto(userRepository.save(existingUser));
    }

    @Override
    public boolean isAdmin(String username) {
        return userRepository.findByUsername(username).isPresent() &&
                userRepository.findByUsername(username).get().getRole().equals(UserRole.ADMIN);
    }
}
