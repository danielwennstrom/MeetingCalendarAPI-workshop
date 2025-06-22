package org.example.meetingcalendarapi.controller;

import org.example.meetingcalendarapi.dto.MeetingDto;
import org.example.meetingcalendarapi.service.MeetingService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {
    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MeetingDto> getAllMeetings() {
        return meetingService.getAll();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MeetingDto getMeetingById(@PathVariable Long id) {
        return meetingService.getById(id);
    }
    
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) 
    public MeetingDto createMeeting(@RequestBody MeetingDto meetingDto, Authentication authentication) {
        String username = authentication.getName();
        return meetingService.createMeeting(meetingDto, username);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MeetingDto updateMeeting(@PathVariable Long id, @RequestBody MeetingDto meetingDto) {
        return meetingService.updateMeeting(id, meetingDto);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMeeting(@PathVariable Long id) {
        meetingService.deleteMeeting(id);
    }
}
