package ru.itis.context.forms;

import lombok.*;
import ru.itis.context.models.enums.Status;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {

    @Min(value = 3, message = "{errors.incorrect.userName}")
    @NotNull(message = "{errors.null.userName}")
    private String userName;

    @Max(value = 20, message = "{errors.incorrect.password")
    @NotNull(message = "{errors.null.password}")
    private String password;

    @Email(message = "{errors.incorrect.email}")
    @NotNull(message = "{errors.null.email}")
    private String email;
}
