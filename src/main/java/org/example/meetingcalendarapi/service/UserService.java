package org.example.meetingcalendarapi.service;

import org.example.meetingcalendarapi.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getById(Long id);
    List<UserDto> getAll();
}
