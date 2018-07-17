/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.view.bericht;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author dachs
 */
@Named(value = "berichtBean")
@SessionScoped
public class berichtBean implements Serializable {

    /**
     * Creates a new instance of berichtBean
     */
    public berichtBean() {
    }
    
}
