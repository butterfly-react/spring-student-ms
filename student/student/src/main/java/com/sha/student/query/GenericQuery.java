package com.sha.student.query;


import com.sha.student.query.GenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenericQuery<T> implements GenericDAO<T> {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public void save(T entity) {
        entityManager.persist(entity);
    }




    @Override
    public T findById(int id, Class<T> entityType) {
        return entityManager.find(entityType, id);
    }

    @Override
    @Transactional
    public void deleteById(int id, Class<T> entityType) {
        T entity = entityManager.find(entityType, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public List<T> findAll(Class<T> entityType) {
        TypedQuery<T> query = entityManager.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e", entityType);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(T entity) {
        entityManager.merge(entity);
    }
}
