/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.view.util;

import de.hspf.hardliner.model.Bericht;
import de.hspf.hardliner.model.Users;
import java.util.Collection;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 *
 * @author dachs
 */
@Stateless
public class DummyBerichtManager {
    private Collection<Bericht> berichte;
    private CreatorUtil util;
    
    @PostConstruct
    public void init() {
        util = new CreatorUtil();
        berichte = util.createBerichtList();
    }
    
    public Users createNewUser(long id, String name, String date, String filiale, int gang) {
        return null;
    }

    public Collection<Bericht> getBerichte() {
        return berichte;
    }

    public void setBericht(Collection<Bericht> berichte) {
        this.berichte = berichte;
    }
    
}
