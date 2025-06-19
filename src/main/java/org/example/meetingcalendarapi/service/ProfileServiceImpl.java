package org.example.meetingcalendarapi.service;

import org.example.meetingcalendarapi.dto.ProfileDto;
import org.example.meetingcalendarapi.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    
    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public ProfileDto getById(Long id) {
        return null;
    }

    @Override
    public List<ProfileDto> getAll() {
        return profileRepository.findAll();
    }
}
