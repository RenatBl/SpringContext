package ru.itis.context.repo;

import org.springframework.stereotype.Repository;
import ru.itis.context.models.User;
import ru.itis.context.models.enums.Role;
import ru.itis.context.models.enums.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UsersRepoImpl implements UsersRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> findByUserName(String login) {
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
    public void updateStatus(User entity) {
        entity.setStatus(Status.CONFIRMED);
        entityManager.merge(entity);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public Optional<User> find(Long id) {
        return Optional.of(entityManager.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    public void save(User entity) {
        entity.setStatus(Status.NOT_CONFIRMED);
        entity.setRole(Role.USER);
        entityManager.persist(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
