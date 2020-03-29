package ru.itis.context.models;

import lombok.*;
import ru.itis.context.models.enums.Role;
import ru.itis.context.models.enums.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    public User(String userName, String password, String email, Status status, Role role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.status = status;
        this.role = role;
    }

    private Long id;
    private String userName;
    private String password;
    private String email;
    private Status status;
    private Role role;
}
