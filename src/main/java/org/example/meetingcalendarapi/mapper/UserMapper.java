package org.example.meetingcalendarapi.mapper;

import org.example.meetingcalendarapi.dto.UserDto;
import org.example.meetingcalendarapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto dto);
    UserDto toDto(User entity);

    List<User> toEntityList(List<UserDto> dtoList);
    List<UserDto> toDtoList(List<User> entityList);

    void updateEntity(UserDto dto, @MappingTarget User entity);
}
