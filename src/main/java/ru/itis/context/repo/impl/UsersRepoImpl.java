package ru.itis.context.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.context.models.User;
import ru.itis.context.models.enums.Role;
import ru.itis.context.models.enums.Status;
import ru.itis.context.repo.UsersRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UsersRepoImpl implements UsersRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> findByUsername(String login) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);

        ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("username"), params));

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        query.setParameter(params, login);
        List<User> queryResult = query.getResultList();
        User returnObject = null;

        if (!queryResult.isEmpty()) {
            returnObject = queryResult.get(0);
        }

        return Optional.ofNullable(returnObject);
    }

    @Override
    public void updateStatus(User user) {
        user.setStatus(Status.CONFIRMED);
        entityManager.merge(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public Optional<User> find(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);
        TypedQuery<User> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void save(User entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Long aLong) {

    }
}