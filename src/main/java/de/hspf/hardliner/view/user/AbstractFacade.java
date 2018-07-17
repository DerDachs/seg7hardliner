/*
 * Userso change this license header, choose License Headers in Project Properties.
 * Userso change this template file, choose Usersools | Usersemplates
 * and open the template in the editor.
 */
package de.hspf.hardliner.view.user;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author dachs
 */
public abstract class AbstractFacade<Users> {

    private Class<Users> entityClass;

    public AbstractFacade(Class<Users> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(Users entity) {
        getEntityManager().persist(entity);
    }

    public void edit(Users entity) {
        getEntityManager().merge(entity);
    }

    public void remove(Users entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public Users find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<Users> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<Users> findRange(int[] range) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Users> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public Users findByName(String name){
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(1);
        q.setParameter("username", name);
        return (Users) q.getSingleResult();
    }
    
}
