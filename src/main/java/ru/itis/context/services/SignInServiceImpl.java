package ru.itis.context.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.context.dto.TokenDto;
import ru.itis.context.forms.AuthForm;
import ru.itis.context.models.User;
import ru.itis.context.repo.UsersRepo;

import java.util.Optional;

@Component
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Value("${jwt.secret}")
    private String secret;

    public TokenDto signIn(AuthForm form) {
        Optional<User> userOptional = usersRepo.findByUserName(form.getUserName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (encoder.matches(form.getPassword(), user.getPassword())) {
                String token = Jwts.builder()
                        .setSubject(user.getId().toString())
                        .claim("username", user.getUsername())
                        .claim("role", user.getRole().name())
                        .claim("status", user.getStatus().name())
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact();
                return new TokenDto(token);
            } else throw new AccessDeniedException("Wrong password");
        } else throw new AccessDeniedException("User not found");
    }
}
