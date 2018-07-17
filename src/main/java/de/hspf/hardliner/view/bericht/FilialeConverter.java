/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.view.bericht;

import de.hspf.hardliner.model.Filiale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author dachs
 */
public class FilialeConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Long id = new Long(string);
        FilialeController controller = (FilialeController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "filiale");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Filiale) {
            Filiale o = (Filiale) object;
            return o.getFilialid() == null ? "" : o.getFilialid().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: de.hspf.hardliner.model.Filiale");
        }
    }
    
}
