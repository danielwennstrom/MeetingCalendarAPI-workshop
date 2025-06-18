package org.example.meetingcalendarapi.service;

import org.example.meetingcalendarapi.dto.MeetingDto;
import org.example.meetingcalendarapi.mapper.MeetingMapper;
import org.example.meetingcalendarapi.model.Meeting;
import org.example.meetingcalendarapi.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;
    
    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository, MeetingMapper meetingMapper) {
        this.meetingRepository = meetingRepository;
        this.meetingMapper = meetingMapper;
    }

    @Override
    public MeetingDto createMeeting(MeetingDto meetingDto) {
        Meeting mappedMeeting = meetingMapper.toEntity(meetingDto);
        return meetingMapper.toDto(meetingRepository.save(mappedMeeting));
    }

    @Override
    public List<MeetingDto> findAll() {
        return meetingMapper.toDtoList((List<Meeting>) meetingRepository.findAll());
    }
}
