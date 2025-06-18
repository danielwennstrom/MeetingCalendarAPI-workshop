package org.example.meetingcalendarapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum MeetingLevel {
    TEAM, DEVELOPMENT, GENERAL;

    @JsonCreator
    public static MeetingLevel fromString(String value) {
        return Arrays.stream(MeetingLevel.values())
                .filter(e -> e.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid meeting level: " + value));
    }
}
