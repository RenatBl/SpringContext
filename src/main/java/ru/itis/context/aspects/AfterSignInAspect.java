package ru.itis.context.aspects;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.context.dto.TokenDto;
import ru.itis.context.models.Chat;
import ru.itis.context.models.User;
import ru.itis.context.repo.ChatRepo;
import ru.itis.context.repo.UsersRepo;

@Aspect
@Component
public class AfterSignInAspect {

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Value("${jwt.secret}")
    private String secret;

    @AfterReturning(pointcut = "execution(* ru.itis.context.services.impl.SignInServiceImpl.signIn())",
            returning = "tokenDto")
    public void createChat(TokenDto tokenDto) {
        String token = tokenDto.getToken();

        Claims claims;
        try {
            claims =  Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Bad token");
        }

        User user = usersRepo.findByUserName(claims.get("username", String.class)).orElseThrow(() ->
                    new IllegalArgumentException("User not found")
                );

        if (!chatRepo.findByUser(user).isPresent()) {
            chatRepo.save(Chat.builder()
                    .owner(user)
                    .build());

            System.out.println("_____________");
            System.out.println("Chat created");
        } else {
            System.out.println("_____________");
            System.out.println("Chat exist");
        }
    }
}
