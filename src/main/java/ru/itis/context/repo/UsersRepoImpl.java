package ru.itis.context.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.itis.context.models.User;
import ru.itis.context.models.enums.Role;
import ru.itis.context.models.enums.Status;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepoImpl implements UsersRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (row, rowNumber) ->
            User.builder()
                    .id(row.getLong("id"))
                    .username(row.getString("username"))
                    .password(row.getString("hashed_password"))
                    .email(row.getString("email"))
                    .status(Status.valueOf(row.getString("status")))
                    .role(Role.valueOf(row.getString("role")))
                    .build();

    private static final String SQL_SELECT_ALL = "select * from users";
    private static final String SQL_DELETE_BY_ID = "delete from users where id = ?";
    private static final String SQL_SELECT_BY_ID = "select * from users where id = ?";
    private static final String SQL_SELECT_BY_USERNAME = "select * from users where username = ?";
    private static final String SQL_INSERT = "insert into users (username, hashed_password, email, status, role) " +
            "VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE_STATUS = "update users set status = ? where id = ?";
    private static final String SQL_UPDATE = "update users set username = ?, hashed_password = ?, email = ?, " +
            "status = ?, role = ? where id = ?";

    @Override
    public Optional<User> findByUserName(String login) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_USERNAME, new Object[]{login},
                    userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateStatus(User user) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_UPDATE_STATUS);
            statement.setString(1, user.getStatus().name());
            statement.setLong(2, user.getId());
            return statement;
        });
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_UPDATE);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getStatus().name());
            statement.setString(5, user.getRole().name());
            statement.setLong(6, user.getId());
            return statement;
        });
    }

    @Override
    public Optional<User> find(Long id) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public void save(User entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getEmail());
            statement.setString(4, Status.NOT_CONFIRMED.name());
            statement.setString(5, Role.USER.name());
            return statement;
        }, keyHolder);

        entity.setId((Long)keyHolder.getKey());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.query(SQL_DELETE_BY_ID, new Object[]{id}, userRowMapper);
    }
}