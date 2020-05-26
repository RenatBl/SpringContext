package ru.itis.context.repo.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.context.dto.CookieDto;
import ru.itis.context.models.User;
import ru.itis.context.repo.CookieRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CookieRepoImpl implements CookieRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<CookieDto> findByParam(String param) {
        return Optional.empty();
    }

    @Override
    public Optional<CookieDto> findByUser(User user) {
        return Optional.empty();
    }

    @Override
    public void deleteByUser(User user) {

    }

    @Override
    public Optional<CookieDto> find(Long id) {
        return Optional.ofNullable(entityManager.find(CookieDto.class, id));
    }

    @Override
    public List<CookieDto> findAll() {
        return null;
    }

    @Override
    public void save(CookieDto entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(find(id));
    }
}
