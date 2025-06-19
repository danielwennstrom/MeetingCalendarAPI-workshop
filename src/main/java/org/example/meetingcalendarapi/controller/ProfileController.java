package org.example.meetingcalendarapi.controller;

import org.example.meetingcalendarapi.dto.ProfileDto;
import org.example.meetingcalendarapi.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProfileDto> getAllProfiles() {
        return profileService.getAll();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfileDto getProfileById(@PathVariable Long id) {
        return profileService.getById(id);
    }
}
