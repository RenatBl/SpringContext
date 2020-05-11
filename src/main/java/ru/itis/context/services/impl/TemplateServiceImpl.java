package ru.itis.context.services.impl;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ru.itis.context.services.TemplateService;

import java.util.Map;

@Component
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private Configuration fmConfiguration;

    @Override
    public String getContentFromTemplate(Map<String, Object> model, String templateName) {
        StringBuilder content = new StringBuilder();
        try {
            content.append(FreeMarkerTemplateUtils
                    .processTemplateIntoString(fmConfiguration.getTemplate(templateName), model));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return content.toString();
    }
}
