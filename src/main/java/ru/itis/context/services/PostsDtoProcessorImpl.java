package ru.itis.context.services;

import org.springframework.stereotype.Service;
import ru.itis.context.dto.PostDTO;
import ru.itis.context.models.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostsDtoProcessorImpl implements PostsDtoProcessor {

    @Override
    public List<PostDTO> getDto(List<Post> posts) {
        List<PostDTO> dtoList = new ArrayList<>();
        for (Post post: posts) {
            LocalDateTime dateTime = post.getDateOfCreating();
            String hours;
            String minutes;
            String month;
            String day;
            if (dateTime.getHour() < 10) {
                hours = "0" + dateTime.getHour();
            } else {
                hours = "" + dateTime.getHour();
            }
            if (dateTime.getMinute() < 10) {
                minutes = "0" + dateTime.getMinute();
            } else {
                minutes = "" + dateTime.getMinute();
            }
            if (dateTime.getMonthValue() < 10) {
                month = "0" + dateTime.getMonthValue();
            } else {
                month = "" + dateTime.getMonthValue();
            }
            if (dateTime.getDayOfMonth() < 10) {
                day = "0" + dateTime.getDayOfMonth();
            } else {
                day = "" + dateTime.getDayOfMonth();
            }

            dtoList.add(PostDTO.builder()
                    .header(post.getHeader())
                    .content(post.getContent())
                    .date(hours + ":" + minutes + " " + day + "." + month + "." + dateTime.getYear())
                    .build());
        }
        return dtoList;
    }
}
