package ru.itis.context.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.context.forms.PostForm;
import ru.itis.context.models.Post;
import ru.itis.context.repo.PostsRepo;
import ru.itis.context.services.PostsService;

import java.util.List;
import java.util.Optional;

@Component
public class PostsServiceImpl implements PostsService {
    @Autowired
    private PostsRepo postsRepo;

    public Optional<Post> getPostByHeader(String header) {
        return postsRepo.findByHeader(header);
    }

    public void addNewPost(PostForm form) {
        postsRepo.save(Post.builder()
                .header(form.getHeader())
                .content(form.getContent())
                .owner(form.getOwner())
                .build());
    }

    public List<Post> getAllPosts(Long owner, String sort) {
        List<Post> posts = postsRepo.findAllByOwner(owner);
        if (sort == null) {
            return posts;
        } else if (sort.equals("desc")) {
            posts.sort((o1, o2) -> {
                if (o1.getDateOfCreating().isBefore(o2.getDateOfCreating())) {
                    return 1;
                } else if (o1.getDateOfCreating().isAfter(o2.getDateOfCreating())) {
                    return -1;
                } else {
                    return 0;
                }
            });
            return posts;
        } else {
            posts.sort((o1, o2) -> {
                if (o1.getDateOfCreating().isBefore(o2.getDateOfCreating())) {
                    return -1;
                } else if (o1.getDateOfCreating().isAfter(o2.getDateOfCreating())) {
                    return 1;
                } else {
                    return 0;
                }
            });
            return posts;
        }
    }

    public void deletePost(Long id) {
        postsRepo.delete(id);
    }
}
