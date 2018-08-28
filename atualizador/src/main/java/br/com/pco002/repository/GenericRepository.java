package br.com.pco002.repository;

import br.com.pco002.model.GenericEntity;
import javax.persistence.EntityManager;

public class GenericRepository<T extends GenericEntity> {

    private static final EntityManager manager = ConnectionFactory.getEntityManager();

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

    static EntityManager getManager() {
        return manager;
    }
}
