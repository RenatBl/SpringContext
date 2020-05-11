package ru.itis.context.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.context.models.User;
import ru.itis.context.repo.ChatRepo;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String userName;
    private String email;
    private Integer postsQuantity;

    public static UserDto get(User user, String form) {
        switch (form) {
            case "all": {
                return UserDto.builder()
                        .id(user.getId())
                        .userName(user.getUsername())
                        .build();
            }
            case "name": {
                return UserDto.builder()
                        .userName(user.getUsername())
                        .build();
            }
            default: return null;
        }
    }
}
