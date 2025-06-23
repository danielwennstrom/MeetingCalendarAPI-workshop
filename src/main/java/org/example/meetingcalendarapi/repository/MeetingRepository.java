package org.example.meetingcalendarapi.repository;

import org.example.meetingcalendarapi.model.Meeting;
import org.example.meetingcalendarapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    @Query("SELECT m FROM Meeting m JOIN m.participants p WHERE m.dateTime > :dateTime AND p IN :participants")
    List<Meeting> findMeetingsByDateTimeIsAfterAndParticipants(@Param("dateTime") ZonedDateTime dateTime, 
                                                               @Param("participants") List<User> participants);
    @Query("SELECT m FROM Meeting m JOIN m.participants p WHERE m.dateTime BETWEEN :dateTimeAfter AND :dateTimeBefore AND p IN :participants")
    List<Meeting> findMeetingsByDateTimeBetweenAndParticipants(@Param("dateTimeAfter") ZonedDateTime dateTimeAfter, 
                                                               @Param("dateTimeBefore") ZonedDateTime dateTimeBefore, 
                                                               List<User> participants);
}
