package org.example.meetingcalendarapi.service;

import org.example.meetingcalendarapi.dto.MeetingDto;
import org.example.meetingcalendarapi.dto.UserDto;

import java.time.ZonedDateTime;
import java.util.List;

public interface MeetingService {
    MeetingDto createMeeting(MeetingDto meetingDto, String creatorUsername);
    MeetingDto updateMeeting(Long id, MeetingDto meetingDto);
    void deleteMeeting(Long id);
    MeetingDto getById(Long id);
    List<MeetingDto> getAll();
    List<MeetingDto> getMeetingsByDateTimeIsAfterAndParticipants(ZonedDateTime zonedDateTime, List<Long> participantIds);
}
