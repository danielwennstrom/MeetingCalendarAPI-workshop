package org.example.meetingcalendarapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private String level;
    private String participants;
    
    public Meeting() {
    }

    public Meeting(String title, String description, LocalDate date, LocalTime time, String level, String participants) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.level = level;
        this.participants = participants;
    }
}

