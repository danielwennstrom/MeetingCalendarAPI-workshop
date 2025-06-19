package org.example.meetingcalendarapi.mapper;
import org.example.meetingcalendarapi.dto.MeetingDto;
import org.example.meetingcalendarapi.model.Meeting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MeetingMapper {
    @Mapping(source = "level", target = "level")
    Meeting toEntity(MeetingDto dto);
    MeetingDto toDto(Meeting entity);
    
    List<Meeting> toEntityList(List<MeetingDto> dtoList);
    List<MeetingDto> toDtoList(List<Meeting> entityList);
    
    void updateEntity(MeetingDto dto, @MappingTarget Meeting entity);
}
