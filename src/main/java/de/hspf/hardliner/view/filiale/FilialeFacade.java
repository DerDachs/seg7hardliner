/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.view.filiale;

import de.hspf.hardliner.model.Filiale;
import de.hspf.hardliner.view.bericht.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author dachs
 */
@Stateless
public class FilialeFacade extends AbstractFacade<Filiale> {

    private Class<Filiale> entityClass;

    @PersistenceContext(unitName = "hardlinerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FilialeFacade() {
        super(Filiale.class);
    }

    public List<Filiale> findRegion(String bland) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(1);
        q.setParameter("Bundesland", bland);
        return (List<Filiale>) q.getResultList();
    }

    public List<Filiale> findFiliale(String region) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery(cq);
        q.setParameter("Region", region);
        return (List<Filiale>) q.getResultList();
    }

    public List<Filiale> findBundesland() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery(cq);
        return (List<Filiale>) q.getResultList();
    }

}
