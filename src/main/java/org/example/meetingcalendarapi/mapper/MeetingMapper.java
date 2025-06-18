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
    Meeting toEntity(MeetingDto meetingDto);
    MeetingDto toDto(Meeting meeting);
    
    List<Meeting> toEntityList(List<MeetingDto> dtoList);
    List<MeetingDto> toDtoList(List<Meeting> meetingList);
    
    void updateEntity(MeetingDto dto, @MappingTarget Meeting entity);
}
