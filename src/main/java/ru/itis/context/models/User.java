package ru.itis.context.models;

import lombok.*;
import ru.itis.context.models.enums.Role;
import ru.itis.context.models.enums.Status;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "hashed_password")
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Role role;
}
