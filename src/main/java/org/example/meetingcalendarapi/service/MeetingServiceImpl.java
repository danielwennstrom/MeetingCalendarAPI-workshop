package org.example.meetingcalendarapi.service;

import org.example.meetingcalendarapi.dto.MeetingDto;
import org.example.meetingcalendarapi.dto.UserDto;
import org.example.meetingcalendarapi.mapper.MeetingMapper;
import org.example.meetingcalendarapi.mapper.UserMapper;
import org.example.meetingcalendarapi.model.Meeting;
import org.example.meetingcalendarapi.model.User;
import org.example.meetingcalendarapi.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;
    private final UserService userService;
    private final UserMapper userMapper;
    
    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository, MeetingMapper meetingMapper, UserService userService, UserMapper userMapper) {
        this.meetingRepository = meetingRepository;
        this.meetingMapper = meetingMapper;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public MeetingDto createMeeting(MeetingDto meetingDto, String creatorUsername) {
        Meeting mappedMeeting = meetingMapper.toEntity(meetingDto);

        UserDto creatorDto = userService.getByUsername(creatorUsername);
        User creator = userMapper.toEntity(creatorDto);
        mappedMeeting.setCreator(creator);

        List<UserDto> participantsDtoList = userService.getAllById(meetingDto.getParticipantIds());
        List<User> participants = participantsDtoList.stream()
                .map(userMapper::toEntity)
                .collect(Collectors.toList());
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
        return meetingMapper.toDtoList(meetingRepository.findAll());
    }

    @Override
    public List<MeetingDto> getMeetingsByDateTimeIsAfterAndParticipants(ZonedDateTime zonedDateTime, List<Long> participantIds) {
        List<User> participants = userMapper.toEntityList(userService.getAllById(participantIds));
        List<Meeting> meetings = meetingRepository.findMeetingsByDateTimeIsAfterAndParticipants(zonedDateTime, participants);
        
        return meetingMapper.toDtoList(meetings);
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
