package ru.itis.context.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.context.forms.UserForm;
import ru.itis.context.models.Mail;
import ru.itis.context.services.AuthService;
import ru.itis.context.services.LinkService;
import ru.itis.context.services.MessageService;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class AfterRegistrationAspect {
    @Autowired
    private MessageService messageService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private AuthService authService;

    @AfterReturning(pointcut = "execution(* ru.itis.context.controllers.SignUpController.signUpUser(..))",
            returning = "form")
    public void sendMessage(UserForm form) {
        Long id = authService.find(form.getUserName()).get().getId();

        Map<String, Object> model = new HashMap<>();
        model.put("username", form.getUserName());
        String key = linkService.generateLink(id).getKey();
        model.put("link", "http://localhost:8080/confirm?key=" + key);
        System.out.println("Hello");
        messageService.sendMail(Mail.builder()
                .mailTo(form.getEmail())
                .mailSubject("Добро пожаловать на наш сервис!")
                .model(model)
                .build(), "signUpMail.txt");
    }
}
