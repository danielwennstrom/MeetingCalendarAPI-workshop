package org.example.meetingcalendarapi.mapper;

import org.example.meetingcalendarapi.dto.UserRegisterDto;
import org.example.meetingcalendarapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProfileMapper.class})
public interface UserRegisterMapper {
    User toEntity(UserRegisterDto dto);
}
