package ru.itis.context.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.context.models.User;
import ru.itis.context.security.annotations.AuthenticatedUser;
import ru.itis.context.security.jwt.details.UserDetailsImpl;
import ru.itis.context.services.UsersService;

@RestController
@RequestMapping("/user")
public class UserPageController {

    @Autowired
    private UsersService service;

    @GetMapping
    public ResponseEntity<User> getUserPage(@AuthenticatedUser UserDetailsImpl userDetails1,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails2) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getDetails();

        System.out.println(userDetails1.toString());
        System.out.println(userDetails2.toString());
        
        return ResponseEntity.ok(service.getUserByUsername(userDetails.getUsername()));
    }
}
