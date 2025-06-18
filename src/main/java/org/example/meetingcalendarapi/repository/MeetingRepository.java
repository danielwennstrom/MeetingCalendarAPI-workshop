package org.example.meetingcalendarapi.repository;

import org.example.meetingcalendarapi.model.Meeting;
import org.springframework.data.repository.CrudRepository;

public interface MeetingRepository extends CrudRepository<Meeting, Long> {
}
