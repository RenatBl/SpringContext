package ru.itis.context.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.itis.context.models.Mail;
import ru.itis.context.services.MessageService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
public class AfterFileDownloadAspect {

    @Autowired
    private MessageService messageService;

    @AfterReturning(pointcut = "execution(* ru.itis.context.controllers.FilesController.handleFileUpload(..))",
    returning = "responseEntity")
    public void sendMessage(ResponseEntity<String> responseEntity) {
        String[] body = Objects.requireNonNull(responseEntity.getBody()).split(" ");
        Map<String, Object> model = new HashMap<>();
        model.put("username", body[1]);
        model.put("link", "http://localhost:8080/files/" + body[0]);

        messageService.sendMail(Mail.builder()
                .mailTo(body[2])
                .model(model)
                .mailSubject("Был загружен файл")
                .build(), "fileSaveMail.txt");
    }
}
