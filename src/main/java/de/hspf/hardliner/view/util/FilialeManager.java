/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.view.util;

import de.hspf.hardliner.model.Bericht;
import de.hspf.hardliner.model.Filiale;
import de.hspf.hardliner.model.Users;
import de.hspf.hardliner.view.bericht.BerichtFacade;
import de.hspf.hardliner.view.filiale.FilialeFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author dachs
 */
@Named(value = "filialeManager")
@SessionScoped
public class FilialeManager implements Serializable {

    @EJB
    private FilialeFacade ejbFacade;
    private List<Filiale> items = null;
    private List<Filiale> bland = null;
    private List<Filiale> region = null;
    private List<Filiale> filiale = null;
    private Filiale selected;

    /**
     * Creates a new instance of FilialManager
     */
    public FilialeManager() {
    }

    public Filiale getSelected() {
        return selected;
    }

    public void setSelected(Filiale selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public FilialeFacade getFacade() {
        return ejbFacade;
    }

    public Filiale prepareCreate() {
        selected = new Filiale();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FilialeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FilialeUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FilialeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Filiale> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Filiale> getBland() {
        if (bland == null) {
            bland = getFacade().findDistinct();
        }
        
        return bland;
    }

    public void setBland(List<Filiale> bland) {
        this.bland = bland;
    }

    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Filiale getFiliale(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Filiale> getRegion(String bundesland) {
        if (region == null) {
            region = getFacade().findDistinct( (Object) bundesland);
        }
        
        return region;
    }

    public List<Filiale> getFiliale(String region) {
        if (filiale == null) {
            filiale = getFacade().findDistinctFiliale(region);
        }
        
        return filiale;
    }

    public List<Filiale> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Filiale> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Filiale.class)
    public static class FilialeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FilialeManager controller = (FilialeManager) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "FilialeController");
            return controller.getFiliale(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Filiale) {
                Bericht o = (Bericht) object;
                return getStringKey(o.getBerichtid());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Bericht.class.getName()});
                return null;
            }
        }

    }
}
