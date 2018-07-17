/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.view.filiale;

import de.hspf.hardliner.model.Filiale;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author dachs
 */
@Named(value = "filialeBean")
@ViewScoped
public class FilialeBean {

    @EJB
    private FilialeFacade filialeFacade;
    private String filialeSelected;
    private List<SelectItem> selectFiliale;
    private List<SelectItem> selectRegion;
    private List<SelectItem> selectBundesland;

    /**
     * Creates a new instance of FilialeBean
     */
    public FilialeBean() {
    }

    public List<SelectItem> getBundesland() {
        List<Filiale> f = filialeFacade.findBundesland();
        selectBundesland = new ArrayList<>();
        int i = 0;
        for (Filiale filiale : f) {
            selectBundesland.add(new SelectItem(i, filiale.getBundesland()));
            i++;
        }
        return selectBundesland;
    }

    public List<SelectItem> getRegion(String bland){
        List<Filiale> f = filialeFacade.findRegion(bland);
        selectRegion = new ArrayList<>();
        int i = 0;
        for (Filiale filiale : f) {
            selectRegion.add(new SelectItem(i, filiale.getRegion()));
            i++;
        }        
        return selectRegion;
    }
    
    public List<SelectItem> getFiliale(String region){
        List<Filiale> f = filialeFacade.findFiliale(region);
        selectFiliale = new ArrayList<>();
        int i = 0;
        for (Filiale filiale : f) {
            selectFiliale.add(new SelectItem(i, filiale.getFilialname()));
            i++;
        }        
        return selectFiliale;
    }
    
    public String getFilialeSelected() {
        return filialeSelected;
    }

    public void setFilialeSelected(String filialeSelected) {
        this.filialeSelected = filialeSelected;
    }

}
