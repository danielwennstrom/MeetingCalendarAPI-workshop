package org.example.meetingcalendarapi.controller;

import org.example.meetingcalendarapi.dto.LoginRequest;
import org.example.meetingcalendarapi.dto.TokenRequest;
import org.example.meetingcalendarapi.dto.UserRegisterDto;
import org.example.meetingcalendarapi.service.UserAuthService;
import org.example.meetingcalendarapi.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final UserAuthService userAuthService;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserAuthService userAuthService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userAuthService = userAuthService;
        this.jwtUtils = jwtUtils;
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        String jwt = jwtUtils.generateToken(authentication.getName(), authentication.getAuthorities());

        return ResponseEntity.ok(jwt);
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        userAuthService.registerUser(userRegisterDto);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRegisterDto.getUsername(), userRegisterDto.getPassword())
        );
        String jwt = jwtUtils.generateToken(authentication.getName(), authentication.getAuthorities());
        
        return ResponseEntity.ok(jwt);
    }
    
    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestBody TokenRequest tokenRequest) {
        boolean isValid = jwtUtils.validateToken(tokenRequest.getToken());
        
        return ResponseEntity.ok(isValid);
    }
}
