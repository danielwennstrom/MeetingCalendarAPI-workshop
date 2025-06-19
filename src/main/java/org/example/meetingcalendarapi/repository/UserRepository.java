package org.example.meetingcalendarapi.repository;

import org.example.meetingcalendarapi.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
