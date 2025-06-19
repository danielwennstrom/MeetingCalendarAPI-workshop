package org.example.meetingcalendarapi.repository;

import org.example.meetingcalendarapi.model.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
