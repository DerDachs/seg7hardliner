/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.view.bericht;

import de.hspf.hardliner.model.Bericht;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author dachs
 */
public class BerichtConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Long id = new Long(string);
        BerichtController controller = (BerichtController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "bericht");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Bericht) {
            Bericht o = (Bericht) object;
            return o.getBerichtid() == null ? "" : o.getBerichtid().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: de.hspf.hardliner.model.Bericht");
        }
    }
    
}
