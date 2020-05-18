package ru.itis.context.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.itis.context.models.enums.Role;
import ru.itis.context.models.enums.Status;
import ru.itis.context.security.jwt.details.UserDetailsImpl;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Chat {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    @Transient
    private User admin;

    @PostLoad
    public void insertAdmin() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getDetails();

        if (userDetails.getRole().equals(Role.ADMIN.name())) {
            admin = User.builder()
                    .id(userDetails.getUserId())
                    .username(userDetails.getUsername())
                    .status(Status.valueOf(userDetails.getStatus()))
                    .role(Role.valueOf(userDetails.getRole()))
                    .build();
        }
    }
}
