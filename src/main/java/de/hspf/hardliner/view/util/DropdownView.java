/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.view.util;

import de.hspf.hardliner.model.Filiale;
import de.hspf.hardliner.view.filiale.FilialeFacade;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import org.primefaces.behavior.ajax.AjaxBehavior;

/**
 *
 * @author dachs
 */
@ManagedBean
@ViewScoped
public class DropdownView implements Serializable {

    @EJB
    private FilialeFacade ejbFacade;
    private String selectedBundesland;
    private String selectedRegion;
    private String selectedFiliale;
    private List<SelectItem> bundeslande;
    private List<SelectItem> regionen;
    private List<SelectItem> filialen;
    private FilialeManager fmanager = new FilialeManager();

    @PostConstruct
    public void init() {

    }

    public String getSelectedBundesland() {
        return selectedBundesland;
    }

    public void setSelectedBundesland(String selectedBundesland) {
        this.selectedBundesland = selectedBundesland;
    }

    public String getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(String selectedRegion) {
        this.selectedRegion = selectedRegion;
    }

    public String getSelectedFiliale() {
        return selectedFiliale;
    }

    public void setSelectedFiliale(String selectedFiliale) {
        this.selectedFiliale = selectedFiliale;
    }

    public List<SelectItem> getBundeslande() {
        return bundeslande;
    }

    public void setBundeslande(List<SelectItem> bundeslande) {
        this.bundeslande = bundeslande;
    }

    public List<SelectItem> getRegionen() {
        return regionen;
    }

    public void setRegionen(List<SelectItem> regionen) {
        this.regionen = regionen;
    }

    public List<SelectItem> getFilialen() {
        return filialen;
    }

    public void setFilialen(List<SelectItem> filialen) {
        this.filialen = filialen;
    }

    public void changeBundesland(AjaxBehaviorEvent event) {
        if (selectedBundesland != null){
        regionen = listRegion(selectedBundesland);
        selectedRegion = null;
        selectedFiliale = null;
        }else{
            System.out.println("Hier ging was schief!");
        }

    }

    public void changeRegion(AjaxBehaviorEvent event) {
        filialen = listFiliale(selectedRegion);
        selectedFiliale = null;

    }

    public void displayLocation() {
        FacesMessage msg;
        if (selectedRegion != null && selectedBundesland != null) {
            msg = new FacesMessage("Selected", selectedRegion + " of " + selectedBundesland);
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected.");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private List<SelectItem> listRegion(String bundesland) {
        List<SelectItem> bland = new ArrayList<SelectItem>();

        if (bundesland != null) {
            List<Filiale> filiale = ejbFacade.findDistinct( (Object) bundesland);
            for (Filiale fil : filiale){
                bland.add(new SelectItem(fil.getFilialid(), fil.getRegion()));
            }
        }
        return bland;
    }

    private List<SelectItem> listFiliale(String selectedRegion) {
        List<SelectItem> reg = new ArrayList<SelectItem>();

        if (selectedRegion != null) {
            List<Filiale> filiale = (List<Filiale>) ejbFacade.findDistinctFiliale(selectedRegion);
            for (Filiale f : filiale) {
                reg.add(new SelectItem(f.getFilialid(), f.getFilialname()));
            }
        }
        return reg;
    }
}
