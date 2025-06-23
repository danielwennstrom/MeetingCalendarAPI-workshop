package org.example.meetingcalendarapi.service;

import org.example.meetingcalendarapi.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getById(Long id);
    UserDto getByUsername(String username);
    List<UserDto> getAll();
    List<UserDto> getAllSearchable();
    List<UserDto> getAllById(List<Long> ids);
    UserDto updateUser(Long id, UserDto userDto);
    boolean isAdmin(String username);
}
