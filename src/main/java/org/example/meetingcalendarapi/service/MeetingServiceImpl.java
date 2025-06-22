package org.example.meetingcalendarapi.service;

import org.example.meetingcalendarapi.dto.MeetingDto;
import org.example.meetingcalendarapi.mapper.MeetingMapper;
import org.example.meetingcalendarapi.model.Meeting;
import org.example.meetingcalendarapi.model.User;
import org.example.meetingcalendarapi.repository.MeetingRepository;
import org.example.meetingcalendarapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;
    private final UserRepository userRepository;
    
    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository, MeetingMapper meetingMapper, UserRepository userRepository) {
        this.meetingRepository = meetingRepository;
        this.meetingMapper = meetingMapper;
        this.userRepository = userRepository;
    }

    @Override
    public MeetingDto createMeeting(MeetingDto meetingDto, String creatorUsername) {
        Meeting mappedMeeting = meetingMapper.toEntity(meetingDto);
        User creator = userRepository.findByUsername(creatorUsername).orElseThrow();
        mappedMeeting.setCreator(creator);
        
        List<User> participants = (List<User>) userRepository.findAllById(meetingDto.getParticipantIds());
        mappedMeeting.setParticipants(participants);
        
        return meetingMapper.toDto(meetingRepository.save(mappedMeeting));
    }

    @Override
    public MeetingDto updateMeeting(Long id, MeetingDto meetingDto) {
        Meeting existingMeeting = meetingRepository.findById(id).orElse(null);
        if (existingMeeting == null) {
            throw new IllegalArgumentException("Meeting not found");
        }
        meetingMapper.updateEntity(meetingDto, existingMeeting);

        return meetingMapper.toDto(meetingRepository.save(existingMeeting));
    }
    
    @Override
    public void deleteMeeting(Long id) {
        Meeting existingMeeting = meetingRepository.findById(id).orElse(null);
        if (existingMeeting == null) {
            throw new IllegalArgumentException("Meeting not found");
        }
        
        meetingRepository.delete(existingMeeting);
    }

    @Override
    public List<MeetingDto> getAll() {
        return meetingMapper.toDtoList((List<Meeting>) meetingRepository.findAll());
    }
    
    @Override
    public MeetingDto getById(Long id) {
        Meeting existingMeeting = meetingRepository.findById(id).orElse(null);
        if (existingMeeting == null) {
            throw new IllegalArgumentException("Meeting not found");
        }
        
        return meetingMapper.toDto(existingMeeting);
    }
}
