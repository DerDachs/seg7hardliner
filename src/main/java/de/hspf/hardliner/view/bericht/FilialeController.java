/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.view.bericht;

import de.hspf.hardliner.model.Filiale;
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
public class FilialeController {

    public FilialeController() {
        pagingInfo = new PagingInfo();
        converter = new FilialeConverter();
    }
    private Filiale filiale = null;
    private List<Filiale> filialeItems = null;
    private FilialeFacade jpaController = null;
    private FilialeConverter converter = null;
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

    public FilialeFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (FilialeFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "filialeJpa");
        }
        return jpaController;
    }

    public SelectItem[] getFilialeItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getFilialeItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Filiale getFiliale() {
        if (filiale == null) {
            filiale = (Filiale) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentFiliale", converter, null);
        }
        if (filiale == null) {
            filiale = new Filiale();
        }
        return filiale;
    }

    public String listSetup() {
        reset(true);
        return "filiale_list";
    }

    public String createSetup() {
        reset(false);
        filiale = new Filiale();
        return "filiale_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(filiale);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Filiale was successfully created.");
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
        return scalarSetup("filiale_detail");
    }

    public String editSetup() {
        return scalarSetup("filiale_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        filiale = (Filiale) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentFiliale", converter, null);
        if (filiale == null) {
            String requestFilialeString = JsfUtil.getRequestParameter("jsfcrud.currentFiliale");
            JsfUtil.addErrorMessage("The filiale with id " + requestFilialeString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String filialeString = converter.getAsString(FacesContext.getCurrentInstance(), null, filiale);
        String currentFilialeString = JsfUtil.getRequestParameter("jsfcrud.currentFiliale");
        if (filialeString == null || filialeString.length() == 0 || !filialeString.equals(currentFilialeString)) {
            String outcome = editSetup();
            if ("filiale_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit filiale. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(filiale);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Filiale was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentFiliale");
        Long id = new Long(idAsString);
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
                JsfUtil.addSuccessMessage("Filiale was successfully deleted.");
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
//        String relatedControllerOutcome = relatedControllerOutcome();
//        if ((ERROR)) {
//            return relatedControllerOutcome;
//        }
        return listSetup();
    }

    public List<Filiale> getFilialeItems() {
        if (filialeItems == null) {
            getPagingInfo();
            filialeItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return filialeItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "filiale_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "filiale_list";
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
        filiale = null;
        filialeItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Filiale newFiliale = new Filiale();
        String newFilialeString = converter.getAsString(FacesContext.getCurrentInstance(), null, newFiliale);
        String filialeString = converter.getAsString(FacesContext.getCurrentInstance(), null, filiale);
        if (!newFilialeString.equals(filialeString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
