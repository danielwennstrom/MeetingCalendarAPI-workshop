package org.example.meetingcalendarapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.meetingcalendarapi.enums.UserRole;

@Data
public class UserDto {
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private UserRole role;
    @NotNull
    private ProfileDto profile;
}
