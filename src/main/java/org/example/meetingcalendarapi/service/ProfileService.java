package org.example.meetingcalendarapi.service;

import org.example.meetingcalendarapi.dto.ProfileDto;

import java.util.List;

public interface ProfileService {
    ProfileDto getById(Long id);
    List<ProfileDto> getAll();
}
