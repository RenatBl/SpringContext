package ru.itis.context.repo;

import ru.itis.context.models.FileInfo;

import java.util.Optional;

public interface FileRepo extends CrudRepo<FileInfo, Long> {
    Optional<FileInfo> findByName(String name);
}
