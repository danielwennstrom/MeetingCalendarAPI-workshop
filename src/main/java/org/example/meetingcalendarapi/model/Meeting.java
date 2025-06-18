package org.example.meetingcalendarapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Meeting {
    @Id
    private int id;
    private String title;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private String level;
    private String participants;
    
    public Meeting() {
    }

    public Meeting(int id, String title, String description, LocalDate date, LocalTime time, String level, String participants) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.level = level;
        this.participants = participants;
    }
}

