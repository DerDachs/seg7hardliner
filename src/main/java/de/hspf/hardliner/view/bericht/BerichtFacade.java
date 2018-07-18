/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.view.bericht;

import de.hspf.hardliner.model.Bericht;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dachs
 */
@Stateless
public class BerichtFacade extends AbstractFacade<Bericht> {

    @PersistenceContext(unitName = "hardlinerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BerichtFacade() {
        super(Bericht.class);
    }
    
    @Override
    public void create(Bericht bericht){
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(bericht);
            em.getTransaction().commit();
        } catch (Exception ex){
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
}
