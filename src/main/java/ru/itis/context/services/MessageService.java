package ru.itis.context.services;

import ru.itis.context.models.Mail;

public interface MessageService {
    void sendMail(Mail mail, String templateName);
}
