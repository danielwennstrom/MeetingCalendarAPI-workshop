package org.example.meetingcalendarapi.dto;

import lombok.Data;
import org.example.meetingcalendarapi.enums.MeetingLevel;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class MeetingDto {
    private Long id;
    private String title;
    private String description;
    private ZonedDateTime dateTime;
    private MeetingLevel level;
    private UserDto creator;
    private List<UserDto> participants;
    private List<Long> participantIds;
}
