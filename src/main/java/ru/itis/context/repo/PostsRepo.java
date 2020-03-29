package ru.itis.context.repo;

import ru.itis.context.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostsRepo extends CrudRepo<Post, Long> {
    Optional<Post> findByHeader(String header);
    List<Post> findAllByOwner(Long owner);
}
