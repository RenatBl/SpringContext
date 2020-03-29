package ru.itis.context.services;

import ru.itis.context.models.Link;

public interface LinkService {
    Link generateLink(Long user);
    void deleteLink(Long id);
}
