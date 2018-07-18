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
    private String bundeslandSelected;
    private String regionSelected;
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

    public List<SelectItem> getRegion(){
        List<Filiale> f = filialeFacade.findRegion(bundeslandSelected);
        selectRegion = new ArrayList<>();
        int i = 0;
        for (Filiale filiale : f) {
            selectRegion.add(new SelectItem(i, filiale.getRegion()));
            i++;
        }        
        return selectRegion;
    }
    
    public List<SelectItem> getFiliale(){
        List<Filiale> f = filialeFacade.findFiliale(regionSelected);
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

    public String getBundeslandSelected() {
        return bundeslandSelected;
    }

    public void setBundeslandSelected(String bundeslandSelected) {
        this.bundeslandSelected = bundeslandSelected;
    }

    public String getRegionSelected() {
        return regionSelected;
    }

    public void setRegionSelected(String regionSelected) {
        this.regionSelected = regionSelected;
    }

    public List<SelectItem> getSelectFiliale() {
        return selectFiliale;
    }

    public List<SelectItem> getSelectRegion() {
        return selectRegion;
    }

    public List<SelectItem> getSelectBundesland() {
        return selectBundesland;
    }

    
}
