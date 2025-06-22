package org.example.meetingcalendarapi.service;

import org.example.meetingcalendarapi.dto.MeetingDto;

import java.util.List;

public interface MeetingService {
    MeetingDto createMeeting(MeetingDto meetingDto, String creatorUsername);
    MeetingDto updateMeeting(Long id, MeetingDto meetingDto);
    void deleteMeeting(Long id);
    MeetingDto getById(Long id);
    List<MeetingDto> getAll();
}
