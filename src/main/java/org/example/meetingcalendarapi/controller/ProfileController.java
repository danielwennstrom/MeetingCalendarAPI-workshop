package org.example.meetingcalendarapi.controller;

import org.example.meetingcalendarapi.dto.ProfileDto;
import org.example.meetingcalendarapi.model.Profile;
import org.example.meetingcalendarapi.service.ProfileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    
    public List<ProfileDto> getAllProfiles() {
        return profileService.getAll();
    }
}
