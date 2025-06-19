package org.example.meetingcalendarapi.service;

import org.example.meetingcalendarapi.dto.ProfileDto;
import org.example.meetingcalendarapi.mapper.ProfileMapper;
import org.example.meetingcalendarapi.model.Profile;
import org.example.meetingcalendarapi.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    
    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    @Override
    public ProfileDto getById(Long id) {
        return profileMapper.toDto(profileRepository.findById(id).orElse(null));
    }

    @Override
    public List<ProfileDto> getAll() {
        return profileMapper.toDtoList((List<Profile>) profileRepository.findAll());
    }
}
