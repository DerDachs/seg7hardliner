/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.view.bericht;

import de.hspf.hardliner.model.Bericht;
import de.hspf.hardliner.view.util.CreatorUtil;
import java.io.Serializable;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author dachs
 */
@Named(value = "berichteBean")
@SessionScoped
public class BerichteBean implements Serializable{
    
    private Bericht bericht;
    @Inject
    private CreatorUtil util;
    
    public BerichteBean(){
        
    }

    public Bericht getBericht() {
        return bericht;
    }

    public void setBericht(Bericht bericht) {
        this.bericht = bericht;
    }
    
    public long getBerichtID(){
        return bericht.getId();
    }
    
    public String getBerichtName(){
        return bericht.getName();
    }
    public String getBerichtFiliale(){
        return bericht.getFiliale();
    }
    public int getBerichtGang(){
        return bericht.getGang();
    }
    public String getBerichtDate(){
        return bericht.getDate();
    }
}
