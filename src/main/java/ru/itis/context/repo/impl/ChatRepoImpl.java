package ru.itis.context.repo.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.context.models.Chat;
import ru.itis.context.models.User;
import ru.itis.context.repo.ChatRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ChatRepoImpl implements ChatRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Chat> findByUser(User user) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Chat> criteriaQuery = criteriaBuilder.createQuery(Chat.class);
        Root<Chat> root = criteriaQuery.from(Chat.class);
        criteriaQuery.select(root);

        ParameterExpression<User> params = criteriaBuilder.parameter(User.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("owner"), params));

        TypedQuery<Chat> query = entityManager.createQuery(criteriaQuery);
        query.setParameter(params, user);
        List<Chat> queryResult = query.getResultList();
        Chat returnObject = null;

        if (!queryResult.isEmpty()) {
            returnObject = queryResult.get(0);
        }

        return Optional.ofNullable(returnObject);
    }

    @Override
    public Optional<Chat> find(Long id) {
        return Optional.ofNullable(entityManager.find(Chat.class, id));
    }

    @Override
    public List<Chat> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Chat> cq = cb.createQuery(Chat.class);
        Root<Chat> rootEntry = cq.from(Chat.class);
        CriteriaQuery<Chat> all = cq.select(rootEntry);
        TypedQuery<Chat> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void save(Chat entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Long aLong) {

    }
}
