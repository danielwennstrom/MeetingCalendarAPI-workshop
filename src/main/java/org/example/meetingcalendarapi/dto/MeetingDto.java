package org.example.meetingcalendarapi.dto;

import lombok.Data;
import org.example.meetingcalendarapi.model.Meeting;

import java.util.List;

@Data
public class MeetingDto extends Meeting {
    private List<Long> participantIds;
}
