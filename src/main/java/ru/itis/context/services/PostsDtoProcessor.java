package ru.itis.context.services;

import ru.itis.context.dto.PostDTO;
import ru.itis.context.models.Post;

import java.util.List;

public interface PostsDtoProcessor {
    List<PostDTO> getDto(List<Post> posts);
}
