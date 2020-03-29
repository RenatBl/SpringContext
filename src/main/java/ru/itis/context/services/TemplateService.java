package ru.itis.context.services;

import java.util.Map;

public interface TemplateService {
    String getContentFromTemplate(Map<String, Object> model, String templateName);
}
