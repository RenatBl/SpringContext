package ru.itis.context.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mail {
    private String mailTo;
    private Map<String, Object> model;
    private String mailContent;
    private String mailSubject;
}
