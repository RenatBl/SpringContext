package ru.itis.context.repo;

import java.util.List;
import java.util.Optional;

public interface CrudRepo<V, ID> {
    Optional<V> find(ID id);
    List<V> findAll();
    void save(V entity);
    void delete(ID id);
}
