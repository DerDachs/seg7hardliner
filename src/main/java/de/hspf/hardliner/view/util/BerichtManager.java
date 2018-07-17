/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.view.util;

import de.hspf.hardliner.model.Bericht;
import de.hspf.hardliner.model.Users;
import de.hspf.hardliner.view.bericht.BerichtFacade;
import de.hspf.hardliner.view.user.AbstractFacade;
import de.hspf.hardliner.view.util.JsfUtil.PersistAction;
import de.hspf.hardliner.view.user.UsersFacade;
import de.hspf.hardliner.view.user.UsersFacade;
import de.hspf.hardliner.view.util.JsfUtil;

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
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author dachs
 */
@Named(value = "berichtManager")
@SessionScoped
public class BerichtManager implements Serializable {
    
    @EJB
    private BerichtFacade ejbFacade;
    private List<Bericht> items = null;
    private Bericht selected;

    
    public BerichtManager() {
    }
    
    public Bericht getSelected() {
        return selected;
    }

    public void setSelected(Bericht selected) {
        this.selected = selected;
    }
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
    public BerichtFacade getFacade() {
        return ejbFacade;
    }
    
    public Bericht prepareCreate() {
        selected = new Bericht();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("BerichtCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("BerichtUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("BerichtDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Bericht> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
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

    public Bericht getBericht(java.lang.Long id) {
        return getFacade().find(id);
    }
    
    public List<Bericht> getBerichte(){
        return getFacade().findAll();
    }

    public List<Bericht> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Bericht> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    

    @FacesConverter(forClass = Bericht.class)
    public static class BerichtControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BerichtManager controller = (BerichtManager) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "berichtController");
            return controller.getBericht(getKey(value));
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
            if (object instanceof Users) {
                Bericht o = (Bericht) object;
                return getStringKey(o.getBerichtid());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Bericht.class.getName()});
                return null;
            }
        }

    }
}
