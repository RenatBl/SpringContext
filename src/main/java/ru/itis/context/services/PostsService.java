package ru.itis.context.services;

import ru.itis.context.forms.PostForm;
import ru.itis.context.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostsService {
    Optional<Post> getPostByHeader(String header);
    void addNewPost(PostForm form);
    List<Post> getAllPosts(Long owner);
    void deletePost(Long id);
}
