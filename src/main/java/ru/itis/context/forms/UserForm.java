package ru.itis.context.forms;

import lombok.*;
import ru.itis.context.models.enums.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {
    private String userName;
    private String password;
    private String email;
    private Status status;
}
