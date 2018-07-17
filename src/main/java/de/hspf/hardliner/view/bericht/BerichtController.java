/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.view.bericht;

import de.hspf.hardliner.model.Bericht;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import de.hspf.hardliner.view.bericht.util.JsfUtil;
import de.hspf.hardliner.view.bericht.util.PagingInfo;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author dachs
 */
public class BerichtController {

    public BerichtController() {
        pagingInfo = new PagingInfo();
        converter = new BerichtConverter();
    }
    private Bericht bericht = null;
    private List<Bericht> berichtItems = null;
    private BerichtFacade jpaController = null;
    private BerichtConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "hardlinerPU")
    private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public BerichtFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (BerichtFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "berichtJpa");
        }
        return jpaController;
    }

    public SelectItem[] getBerichtItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getBerichtItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Bericht getBericht() {
        if (bericht == null) {
            bericht = (Bericht) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentBericht", converter, null);
        }
        if (bericht == null) {
            bericht = new Bericht();
        }
        return bericht;
    }

    public String listSetup() {
        reset(true);
        return "bericht_list";
    }

    public String createSetup() {
        reset(false);
        bericht = new Bericht();
        return "bericht_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(bericht);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Bericht was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("bericht_detail");
    }

    public String editSetup() {
        return scalarSetup("bericht_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        bericht = (Bericht) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentBericht", converter, null);
        if (bericht == null) {
            String requestBerichtString = JsfUtil.getRequestParameter("jsfcrud.currentBericht");
            JsfUtil.addErrorMessage("The bericht with id " + requestBerichtString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String berichtString = converter.getAsString(FacesContext.getCurrentInstance(), null, bericht);
        String currentBerichtString = JsfUtil.getRequestParameter("jsfcrud.currentBericht");
        if (berichtString == null || berichtString.length() == 0 || !berichtString.equals(currentBerichtString)) {
            String outcome = editSetup();
            if ("bericht_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit bericht. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(bericht);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Bericht was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentBericht");
        long id = Long.parseLong(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Bericht was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
//        if ((ERROR)) {
//            return relatedControllerOutcome;
//        }
        return listSetup();
    }

    public List<Bericht> getBerichtItems() {
        if (berichtItems == null) {
            getPagingInfo();
            berichtItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return berichtItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "bericht_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "bericht_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        bericht = null;
        berichtItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Bericht newBericht = new Bericht();
        String newBerichtString = converter.getAsString(FacesContext.getCurrentInstance(), null, newBericht);
        String berichtString = converter.getAsString(FacesContext.getCurrentInstance(), null, bericht);
        if (!newBerichtString.equals(berichtString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
