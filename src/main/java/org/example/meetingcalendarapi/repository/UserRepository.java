package org.example.meetingcalendarapi.repository;

import org.example.meetingcalendarapi.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findBySearchEnabledTrue();
}
