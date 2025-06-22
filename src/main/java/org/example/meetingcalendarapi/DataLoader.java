package org.example.meetingcalendarapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.meetingcalendarapi.dto.UserDto;
import org.example.meetingcalendarapi.enums.UserRole;
import org.example.meetingcalendarapi.model.Profile;
import org.example.meetingcalendarapi.model.User;
import org.example.meetingcalendarapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, ObjectMapper objectMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User adminAccount = createAdminAccount(passwordEncoder);
            userRepository.save(adminAccount);
            
            InputStream inputStream = new ClassPathResource("dummyUsers.json").getInputStream();

            List<UserDto> users = objectMapper.readValue(inputStream, new TypeReference<List<UserDto>>() {});

            for (UserDto userDto : users) {
                User user = new User();
                user.setUsername(userDto.getUsername());
                user.setRole(UserRole.valueOf(String.valueOf(userDto.getRole()))); 
                user.setPassword(passwordEncoder.encode(userDto.getUsername()));

                Profile profile = new Profile();
                profile.setName(userDto.getProfile().getName());
                profile.setEmail(userDto.getProfile().getEmail());
                profile.setPhoneNumber(userDto.getProfile().getPhoneNumber());
                profile.setAvatarUrl(userDto.getProfile().getAvatarUrl());
                
                user.setProfile(profile);
                profile.setUser(user);

                userRepository.save(user);
            }
            System.out.println("Users loaded from JSON");
        } else {
            System.out.println("Users already exist, skipping load");
        }
    }

    private static User createAdminAccount(PasswordEncoder passwordEncoder) {
        User adminAccount = new User();
        adminAccount.setUsername("admin");
        adminAccount.setRole(UserRole.ADMIN);
        adminAccount.setPassword(passwordEncoder.encode("L!Bcu4:EHXY;i#2"));

        Profile adminProfile = new Profile();
        adminProfile.setName("Admin");
        adminProfile.setEmail("admin@example.com");
        adminProfile.setPhoneNumber("+1234567890");
        adminProfile.setUser(adminAccount);
        adminAccount.setProfile(adminProfile);
        return adminAccount;
    }
}
