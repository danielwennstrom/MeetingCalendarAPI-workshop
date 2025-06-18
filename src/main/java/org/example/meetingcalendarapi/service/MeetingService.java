package org.example.meetingcalendarapi.service;

import org.example.meetingcalendarapi.dto.MeetingDto;

import java.util.List;

public interface MeetingService {
    MeetingDto createMeeting(MeetingDto meetingDto);
    List<MeetingDto> findAll();
}
