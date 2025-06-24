package org.example.meetingcalendarapi.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.meetingcalendarapi.enums.MeetingLevel;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class MeetingDto {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    @FutureOrPresent
    private ZonedDateTime dateTime;
    @NotNull
    private MeetingLevel level;
    private UserDto creator;
    private List<UserDto> participants;
    private List<Long> participantIds;
}
