/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.view.bericht;

import de.hspf.hardliner.model.Bericht;
import de.hspf.hardliner.view.util.BerichtManager;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author dachs
 */
@Named(value = "berichteBean")
@SessionScoped
public class BerichteBean implements Serializable {

    /**
     * Creates a new instance of BerichteBean
     */
    @Inject
    BerichtManager bmanager;

    public BerichteBean() {
    }

    public List<Bericht> getBerichtList() {
        List<Bericht> berichtList;
        berichtList = bmanager.getBerichte();
        return berichtList;
    }

}
