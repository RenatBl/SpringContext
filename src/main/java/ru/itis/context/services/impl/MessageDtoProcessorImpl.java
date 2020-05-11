package ru.itis.context.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.context.dto.ChatMessageDto;
import ru.itis.context.models.Message;
import ru.itis.context.models.User;
import ru.itis.context.repo.UsersRepo;
import ru.itis.context.services.MessageDtoProcessor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageDtoProcessorImpl implements MessageDtoProcessor {

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public List<ChatMessageDto> processMessage(List<Message> messages) {
        List<ChatMessageDto> messageDtoList = new ArrayList<>();
        for (Message message: messages) {
            String date = "";
            LocalDateTime dateTime = message.getDateTime();

            int hour = dateTime.getHour();
            if (hour >= 10) {
                date += hour;
            } else {
                date += "0" + hour;
            }
            date += ":";
            int minute = dateTime.getMinute();
            if (minute >= 10) {
                date += minute;
            } else {
                date += "0" + minute;
            }
            date += " ";
            int day = dateTime.getDayOfMonth();
            if (day >= 10) {
                date += day;
            } else {
                date += "0" + day;
            }
            date += ".";
            int month = dateTime.getMonthValue();
            if (month >= 10) {
                date += month;
            } else {
                date += "0" + month;
            }
            date += ".";
            int year = dateTime.getYear();
            date += year;

            messageDtoList.add(ChatMessageDto.builder()
                    .id(message.getId())
                    .content(message.getContent())
                    .owner(message.getUser().getUsername())
                    .date(date)
                    .build());
        }
        return messageDtoList;
    }
}
