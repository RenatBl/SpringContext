package ru.itis.context.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.context.forms.PostForm;
import ru.itis.context.models.Post;
import ru.itis.context.repo.PostsRepo;

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

    public List<Post> getAllPosts(Long owner) {
        return postsRepo.findAllByOwner(owner);
    }

    public void deletePost(Long id) {
        postsRepo.delete(id);
    }
}
