package br.com.pco002.repository;

import br.com.pco002.model.GenericEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

public class GenericRepository<T extends GenericEntity> {

    private EntityManager entityManager = ConnectionFactory.getEntityManager();
    private CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    public void save(T obj) {
        try {
            getManager().getTransaction().begin();
            if (obj.getId() == null) {
                getManager().persist(obj);
            } else {
                getManager().merge(obj);
            }
            getManager().flush();
            getManager().getTransaction().commit();
        } catch (Exception e) {
            getManager().getTransaction().rollback();
        } 
    }

    public void remove(Class<T> clazz, Long id) {
        T t = findById(clazz, id);
        try {
            getManager().getTransaction().begin();
            getManager().remove(t);
            getManager().flush();
            getManager().getTransaction().commit();
        } catch (Exception e) {
            getManager().getTransaction().rollback();
        } 
    }

    public T findById(Class<T> clazz, Long id) {
        return getManager().find(clazz, id);
    }

    public EntityManager getManager() {
        if (entityManager==null)
             entityManager = ConnectionFactory.getEntityManager();
        return entityManager;
    }
    
    public CriteriaBuilder getCriteriaBuilder() {
        if(criteriaBuilder==null)
            criteriaBuilder = entityManager.getCriteriaBuilder();
        return criteriaBuilder;
    }
}
