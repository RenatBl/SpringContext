package ru.itis.context.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.itis.context.models.Link;
import ru.itis.context.repo.LinkRepo;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class LinkRepoImpl implements LinkRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Link> linkRowMapper = (row, rowNumber) ->
        Link.builder()
                .id(row.getLong("id"))
                .key(row.getString("link_key"))
                .user(row.getLong("user_id"))
                .build();

    private static final String SQL_DELETE_BY_ID = "delete from links where id = ?";
    private static final String SQL_SELECT_BY_ID = "select * from links where id = ?";
    private static final String SQL_SELECT_BY_KEY = "select * from links where link_key = ?";
    private static final String SQL_INSERT = "insert into links (link_key, user_id) values (?,?)";

    @Override
    public Optional<Link> find(Long id) {
        try {
            Link link = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, linkRowMapper);
            return Optional.ofNullable(link);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Link> findAll() {
        return null;
    }

    @Override
    public void save(Link entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, entity.getKey());
            statement.setLong(2, entity.getUser());
            return statement;
        }, keyHolder);

        entity.setId((Long)keyHolder.getKey());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_DELETE_BY_ID);
            statement.setLong(1, id);
            return statement;
        });
    }

    @Override
    public Optional<Link> findByKey(String key) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_KEY, new Object[]{key},
                    linkRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
