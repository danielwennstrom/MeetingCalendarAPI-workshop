package org.example.meetingcalendarapi.controller;

import org.example.meetingcalendarapi.dto.UserDto;
import org.example.meetingcalendarapi.mapper.UserMapper;
import org.example.meetingcalendarapi.model.User;
import org.example.meetingcalendarapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable Long id, Authentication authentication) {
        String loggedInUsername = authentication.getName();
        UserDto requestedUser = userService.getById(id);
        if (!requestedUser.getUsername().equals(loggedInUsername) && !userService.isAdmin(loggedInUsername)) {
            throw new RuntimeException("Unauthorized access");
        }
        
        return userService.getById(id);
    }
    
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getCurrentUser(Authentication authentication) {
        String loggedInUsername = authentication.getName();
        UserDto currentUser = userService.getByUsername(loggedInUsername);
        if (!currentUser.getUsername().equals(loggedInUsername)) {
            throw new RuntimeException("Unauthorized access");
        }
        
        return currentUser;
    }
}
