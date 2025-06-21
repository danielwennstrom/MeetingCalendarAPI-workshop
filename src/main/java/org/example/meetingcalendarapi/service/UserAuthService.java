package org.example.meetingcalendarapi.service;

import org.example.meetingcalendarapi.dto.UserDto;
import org.example.meetingcalendarapi.dto.UserRegisterDto;

public interface UserAuthService {
    UserDto getUserByUsername(String username);
    UserDto registerUser(UserRegisterDto userRegisterDto);
}
