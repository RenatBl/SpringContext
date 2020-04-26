package ru.itis.context.models;

import lombok.*;
import ru.itis.context.models.enums.Role;
import ru.itis.context.models.enums.Status;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    public User(String username, String password, String email, Status status, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.role = role;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Role role;
}
