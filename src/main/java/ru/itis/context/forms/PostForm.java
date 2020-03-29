package ru.itis.context.forms;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostForm {
    private String header;
    private String content;
    private Long owner;
}
