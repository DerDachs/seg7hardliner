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
    private List<String> selectFiliale;
    private List<String> selectRegion;
    private List<String> selectBundesland;

    /**
     * Creates a new instance of FilialeBean
     */
    public FilialeBean() {
    }

    public List<String> getBundesland() {
        List<Filiale> f = filialeFacade.findBundesland();
        selectBundesland = new ArrayList<>();

        f.forEach((filiale) -> {
            selectBundesland.add(filiale.getBundesland());
        });
        return selectBundesland;
    }

    public List<String> getRegion(){
        List<Filiale> f = filialeFacade.findRegion(bundeslandSelected);
        selectRegion = new ArrayList<>();
        f.forEach((filiale) -> {
            selectRegion.add( filiale.getRegion());
        });        
        return selectRegion;
    }
    
    public List<String> getFiliale(){
        List<Filiale> f = filialeFacade.findFiliale(regionSelected);
        selectFiliale = new ArrayList<>();
        f.forEach((filiale) -> {
            selectFiliale.add(filiale.getFilialname());
        });        
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

    public List<String> getSelectFiliale() {
        return selectFiliale;
    }

    public List<String> getSelectRegion() {
        return selectRegion;
    }

    public List<String> getSelectBundesland() {
        return selectBundesland;
    }

    
}
