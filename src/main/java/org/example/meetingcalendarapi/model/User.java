package org.example.meetingcalendarapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.example.meetingcalendarapi.enums.UserRole;

import java.util.List;

@Entity
@Data
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String password;
    private UserRole role;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Profile profile;
    @ManyToMany(mappedBy = "participants")
    private List<Meeting> meetings;
    
    public User(String username, String password, Profile profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
    }
    
    public User() { }
    
    @PrePersist
    private void prePersist() {
        if (this.role == null) {
            this.role = UserRole.USER;
        }
    }
}
