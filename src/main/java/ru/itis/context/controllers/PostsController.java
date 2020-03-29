package ru.itis.context.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
import ru.itis.context.services.PostsService;

import java.util.List;

@Controller
public class PostsController {
    @Autowired
    private PostsService service;

    @GetMapping(value = "/posts")
    public String getPosts(Model model, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long ownerId = userDetails.getUser().getId();
        List<Post> posts = service.getAllPosts(ownerId);
            model.addAttribute("id", ownerId);
            model.addAttribute("owner", true);
        if (posts != null) {
            model.addAttribute("posts", posts);
        }
        return "posts";
    }

    @PostMapping("/newPost")
    public String newPost(@ModelAttribute("post") PostForm form, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        form.setOwner(userDetails.getUser().getId());
        service.addNewPost(form);
        return "redirect:/posts";
    }
}
