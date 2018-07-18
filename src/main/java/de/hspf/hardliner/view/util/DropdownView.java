/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.view.util;

import de.hspf.hardliner.model.Filiale;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author dachs
 */
@Named(value = "dropdownView")
@ViewScoped
public class DropdownView implements Serializable {

    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
    private Map<String, Map<String, Map<String, String>>> dat = new HashMap<String, Map<String, Map<String, String>>>();
    private String bundesland;
    private String region;
    private String filiale;
    private Map<String, String> bundeslande;
    private Map<String, String> regionen;
    private Map<String, String> filialen;
    private FilialeManager fmanager = new FilialeManager();

    @PostConstruct
    public void init() {
        bundeslande = new HashMap<String, String>();
        bundeslande.put("Baden-Württemberg", "Baden-Württemberg");
        bundeslande.put("Bayern", "Bayern");
        bundeslande.put("Hessen", "Hessen");

        Map<String, String> map = new HashMap<String, String>();
        List<Filiale> flist = fmanager.getFacade().findRegion("Baden-Württemberg");
        for (Filiale f : flist) {
            map.put(f.getRegion(), f.getRegion());
        }
        data.put("Baden-Württemberg", map);

        map = new HashMap<String, String>();
        List<Filiale> fliste = fmanager.getFacade().findRegion("Bayern");
        for (Filiale f : fliste) {
            map.put(f.getRegion(), f.getRegion());
        }
        data.put("Bayern", map);

        map = new HashMap<String, String>();
        List<Filiale> flisten = fmanager.getFacade().findRegion("Hessen");
        for (Filiale f : flisten) {
            map.put(f.getRegion(), f.getRegion());
        }
        data.put("Hessen", map);
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public String getBundesland() {
        return bundesland;
    }

    public void setBundesland(String bundesland) {
        this.bundesland = bundesland;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFiliale() {
        return filiale;
    }

    public void setFiliale(String filiale) {
        this.filiale = filiale;
    }

    public Map<String, String> getBundeslande() {
        return bundeslande;
    }

    public void setBundeslande(Map<String, String> bundeslande) {
        this.bundeslande = bundeslande;
    }

    public Map<String, String> getRegionen() {
        return regionen;
    }

    public void setRegionen(Map<String, String> regionen) {
        this.regionen = regionen;
    }

    public Map<String, String> getFilialen() {
        return filialen;
    }

    public void setFilialen(Map<String, String> filialen) {
        this.filialen = filialen;
    }

    

    public void onBundeslandChange() {
        if (bundesland != null && !bundesland.equals("")) {
            regionen = data.get(bundesland);
        } else {
            regionen = new HashMap<String, String>();
        }
    }

    public void displayLocation() {
        FacesMessage msg;
        if (region != null && bundesland != null) {
            msg = new FacesMessage("Selected", region + " of " + bundesland);
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected.");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
