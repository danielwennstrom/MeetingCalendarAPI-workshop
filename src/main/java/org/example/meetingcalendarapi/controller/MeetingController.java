package org.example.meetingcalendarapi.controller;

import org.example.meetingcalendarapi.dto.MeetingDto;
import org.example.meetingcalendarapi.service.MeetingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
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
        return meetingService.findAll();
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) 
    public MeetingDto createMeeting(@RequestBody MeetingDto meetingDto) {
        return meetingService.createMeeting(meetingDto);
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
