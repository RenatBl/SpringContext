package ru.itis.context.repo;

import ru.itis.context.dto.CookieDto;
import ru.itis.context.models.User;

import java.util.Optional;

public interface CookieRepo extends CrudRepo<CookieDto, Long> {
    Optional<CookieDto> findByParam(String param);
    Optional<CookieDto> findByUser(User user);
    void deleteByUser(User user);
}
