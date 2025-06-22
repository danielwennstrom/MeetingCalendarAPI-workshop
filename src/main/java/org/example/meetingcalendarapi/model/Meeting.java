package org.example.meetingcalendarapi.model;

import jakarta.persistence.*;
import org.example.meetingcalendarapi.enums.MeetingLevel;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToMany
    @JoinTable(
            name = "meeting_participants",
            joinColumns = @JoinColumn(name = "meeting_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
    
    public Meeting() {
    }

    public Meeting(String title, String description, ZonedDateTime dateTime, MeetingLevel level, List<User> participants) {
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.level = level;
        this.participants = participants;
    }
}

