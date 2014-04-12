package com.bc.is.controller;

import com.bc.is.entity.Lov;
import com.bc.is.controller.util.JsfUtil;
import com.bc.is.controller.util.JsfUtil.PersistAction;
import com.bc.is.services.LovFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "lovController")
@SessionScoped
public class LovController implements Serializable {

    @EJB
    private com.bc.is.services.LovFacade ejbFacade;
    private List<Lov> items = null;
    private Lov selected;

    public LovController() {
    }

    public Lov getSelected() {
        return selected;
    }

    public void setSelected(Lov selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setLovPK(new com.bc.is.entity.LovPK());
    }

    private LovFacade getFacade() {
        return ejbFacade;
    }

    public Lov prepareCreate() {
        selected = new Lov();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Lov").getString("LovCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Lov").getString("LovUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Lov").getString("LovDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Lov> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Lov").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Lov").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Lov> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Lov> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Lov.class)
    public static class LovControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LovController controller = (LovController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "lovController");
            return controller.getFacade().find(getKey(value));
        }

        com.bc.is.entity.LovPK getKey(String value) {
            com.bc.is.entity.LovPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.bc.is.entity.LovPK();
            key.setSubject(values[0]);
            key.setEntry(values[1]);
            return key;
        }

        String getStringKey(com.bc.is.entity.LovPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getSubject());
            sb.append(SEPARATOR);
            sb.append(value.getEntry());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Lov) {
                Lov o = (Lov) object;
                return getStringKey(o.getLovPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Lov.class.getName()});
                return null;
            }
        }

    }

}
