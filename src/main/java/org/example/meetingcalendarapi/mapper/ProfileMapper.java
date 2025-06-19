package org.example.meetingcalendarapi.mapper;

import org.example.meetingcalendarapi.dto.ProfileDto;
import org.example.meetingcalendarapi.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile toEntity(ProfileDto dto);
    ProfileDto toDto(Profile entity);

    List<Profile> toEntityList(List<ProfileDto> dtoList);
    List<ProfileDto> toDtoList(List<Profile> entityList);

    void updateEntity(ProfileDto dto, @MappingTarget Profile entity);
}
