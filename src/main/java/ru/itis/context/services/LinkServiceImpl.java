package ru.itis.context.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.context.models.Link;
import ru.itis.context.repo.LinkRepo;

import java.util.Random;

@Component
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkRepo linkRepo;

    @Override
    public Link generateLink(Long id) {
        String characters = "qwertyuiopasdfghjklzcvbnm123456789QWERTYUIOASDFGHJKLZCVBNM";
        Random rnd = new Random();
        char[] text = new char[20];
        for (int i = 0; i < 20; i++)
        {
            text[i] = characters.charAt(rnd.nextInt(characters.length()));
        }
        Link link = Link.builder()
                .key(new String(text))
                .user(id)
                .build();
        linkRepo.save(link);
        return link;
    }

    @Override
    public void deleteLink(Long id) {
        linkRepo.delete(id);
    }
}
