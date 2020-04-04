package ru.itis.context.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private Long id;
    private Long owner;
    private String header;
    private String content;
    private LocalDateTime dateOfCreating;
}
