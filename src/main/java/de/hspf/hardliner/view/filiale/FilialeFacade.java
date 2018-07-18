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
import javax.persistence.NoResultException;
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

    public List<Filiale> findDistinct() {
        List results = em.createNamedQuery("Filiale.findAllDistinct").getResultList();
        return (List<Filiale>) results;
    }
    
    public List<Filiale> findDistinct(Object bundesland) {
        try {
            Query q = this.em.createNamedQuery("Filiale.findRegionDistinct");
            q.setParameter("bundesland", bundesland);
            return q.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
        
    }

    public List<Filiale> findDistinctFiliale(String region) {
        List results = em.createNamedQuery("Filiale.findFilialeDistinct")
                .setParameter("region", region)
                .getResultList();
        return (List<Filiale>) results;
    }
    
}
