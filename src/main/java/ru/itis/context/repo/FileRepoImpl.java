package ru.itis.context.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.itis.context.models.FileInfo;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class FileRepoImpl implements FileRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<FileInfo> fileInfoRowMapper = (row, rowNumber) ->
            FileInfo.builder()
                    .id(row.getLong("id"))
                    .storageName(row.getString("storageName"))
                    .originalName(row.getString("originalName"))
                    .size(row.getLong("file_size"))
                    .url(row.getString("url"))
                    .type(row.getString("file_type"))
                    .build();

    private static final String SQL_INSERT = "insert into files (storageName, originalName, file_size, url, file_type) " +
            "values (?,?,?,?,?)";

    private static final String SQL_findByName = "select * from files where storageName = ?";

    @Override
    public Optional<FileInfo> findByName(String name) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_findByName, new Object[]{name},
                    fileInfoRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<FileInfo> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<FileInfo> findAll() {
        return null;
    }

    @Override
    public void save(FileInfo entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, entity.getOriginalName());
            statement.setString(2, entity.getStorageName());
            statement.setLong(3, entity.getSize());
            statement.setString(4, entity.getUrl());
            statement.setString(5, entity.getType());
            return statement;
        }, keyHolder);

        entity.setId((Long)keyHolder.getKey());
    }

    @Override
    public void delete(Long aLong) {

    }
}
