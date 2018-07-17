/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.view.filiale;

import de.hspf.hardliner.model.Filiale;
import de.hspf.hardliner.view.bericht.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dachs
 */
@Stateless
public class FilialeFacade extends AbstractFacade<Filiale> {

    @PersistenceContext(unitName = "hardlinerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FilialeFacade() {
        super(Filiale.class);
    }
    
}