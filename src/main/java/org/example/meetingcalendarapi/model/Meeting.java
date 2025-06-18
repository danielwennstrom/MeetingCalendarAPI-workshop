package org.example.meetingcalendarapi.model;

import jakarta.persistence.*;
import org.example.meetingcalendarapi.enums.MeetingLevel;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Data
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private ZonedDateTime dateTime;
    @Enumerated(EnumType.STRING)
    private MeetingLevel level;
    private String participants;
    
    public Meeting() {
    }

    public Meeting(String title, String description, ZonedDateTime dateTime, MeetingLevel level, String participants) {
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.level = level;
        this.participants = participants;
    }
}

