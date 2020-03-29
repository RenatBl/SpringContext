package ru.itis.context.repo;

import ru.itis.context.models.Link;

import java.util.Optional;

public interface LinkRepo extends CrudRepo<Link, Long> {
    Optional<Link> findByKey(String key);
}
