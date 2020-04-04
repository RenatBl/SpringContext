package ru.itis.context.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.context.forms.PostForm;
import ru.itis.context.models.Post;
import ru.itis.context.security.details.UserDetailsImpl;
import ru.itis.context.services.PostsDtoProcessor;
import ru.itis.context.services.PostsService;

import java.util.List;

@Controller
public class PostsController {
    @Autowired
    private PostsService service;

    @Autowired
    private PostsDtoProcessor postsDtoProcessor;

    @GetMapping("/posts")
    public String getPosts(@Nullable @RequestParam("id") Long id,
                           Model model,
                           Authentication authentication,
                           @Nullable @RequestParam("sort") String sort) {
        List<Post> posts;
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long ownerId = userDetails.getUser().getId();
        if (id == null) {
            posts = service.getAllPosts(ownerId, sort);
            model.addAttribute("id", ownerId);
            model.addAttribute("owner", true);
        } else if(id.equals(ownerId)) {
            posts = service.getAllPosts(ownerId, sort);
            model.addAttribute("id", ownerId);
            model.addAttribute("owner", true);
        } else {
            posts = service.getAllPosts(id, sort);
            model.addAttribute("id", id);
            model.addAttribute("owner", false);
        }
        if (posts != null) {
            model.addAttribute("posts", postsDtoProcessor.getDto(posts));
        }
        return "posts";
    }

    @PostMapping("/newPost")
    public String newPost(@ModelAttribute("post") PostForm form, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        form.setOwner(userDetails.getUser().getId());
        service.addNewPost(form);
        return "redirect:/posts?id=" + userDetails.getUser().getId();
    }
}
