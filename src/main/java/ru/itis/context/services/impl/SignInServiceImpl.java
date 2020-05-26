package ru.itis.context.services.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.context.dto.CookieDto;
import ru.itis.context.dto.TokenDto;
import ru.itis.context.forms.AuthForm;
import ru.itis.context.models.User;
import ru.itis.context.repo.CookieRepo;
import ru.itis.context.repo.UsersRepo;
import ru.itis.context.services.SignInService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private CookieRepo cookieRepo;

    @Value("${jwt.secret}")
    private String secret;

    public TokenDto signIn(AuthForm form, HttpServletResponse resp) {
        User user = usersRepo.findByUsername(form.getUserName())
                .orElseThrow(() ->
                        new AccessDeniedException("User not found")
                );
        if (encoder.matches(form.getPassword(), user.getPassword())) {
            String token = Jwts.builder()
                    .setSubject(user.getId().toString())
                    .claim("username", user.getUsername())
                    .claim("role", user.getRole().name())
                    .claim("status", user.getStatus().name())
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
            newCookie(resp, token, user);
            return new TokenDto(token);
        } else throw new AccessDeniedException("Wrong password");
    }

    private void newCookie(HttpServletResponse resp, String token, User user) {
        Cookie cookie = new Cookie("Authorization", token);
        cookie.setMaxAge(5);
        resp.addCookie(cookie);

        cookieRepo.save(CookieDto.builder()
                .parameter(token)
                .user(user)
                .build());
    }
}
