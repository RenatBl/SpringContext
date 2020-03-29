package ru.itis.context.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.itis.context.models.Post;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class PostsRepoImpl implements PostsRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Post> postRowMapper = (row, rowNumber) ->
            Post.builder()
                    .id(row.getLong("id"))
                    .header(row.getString("post_header"))
                    .content(row.getString("post_content"))
                    .owner(row.getLong("owner_id"))
                    .build();

    private static final String SQL_FIND_ALL_BY_OWNER = "select * from posts where owner_id = ?";
    private static final String SQL_SELECT_BY_HEADER = "select * from posts where header = ?";
    private static final String SQL_INSERT = "insert into posts (post_header, post_content, owner_id) VALUES (?,?,?)";
    private static final String SQL_DELETE_BY_ID = "delete from posts where id = ?";

    @Override
    public Optional<Post> findByHeader(String header) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_HEADER, new Object[]{header},
                    postRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Post> findAllByOwner(Long owner) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_OWNER, new Object[]{owner}, postRowMapper);
    }

    @Override
    public Optional<Post> find(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public void save(Post entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, entity.getHeader());
            statement.setString(2, entity.getContent());
            statement.setLong(3, entity.getOwner());
            return statement;
        }, keyHolder);

        entity.setId((Long)keyHolder.getKey());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.query(SQL_DELETE_BY_ID, new Object[]{id}, postRowMapper);
    }
}
