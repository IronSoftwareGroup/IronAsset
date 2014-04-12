package com.bc.is.controller;

import com.bc.is.controller.util.JsfUtil;
import com.bc.is.controller.util.JsfUtil.PersistAction;
import com.bc.is.entity.GlobalProperties;
import com.bc.is.services.GlobalPropertiesFacade;
import com.bc.is.services.ReminderProcessor;
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

@ManagedBean(name = "globalPropertiesController")
@SessionScoped
public class GlobalPropertiesController implements Serializable {

    @EJB
    private com.bc.is.services.GlobalPropertiesFacade ejbFacade;
    private List<GlobalProperties> items = null;
    private GlobalProperties selected;
    
    @EJB
    private ReminderProcessor email;

    public GlobalPropertiesController() {
    }

    public GlobalProperties getSelected() {
        return selected;
    }
    
    public void sendEmail(){
        email.processReminder();
                
    }

    public void setSelected(GlobalProperties selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setGlobalPropertiesPK(new com.bc.is.entity.GlobalPropertiesPK());
    }

    private GlobalPropertiesFacade getFacade() {
        return ejbFacade;
    }

    public GlobalProperties prepareCreate() {
        selected = new GlobalProperties();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/GlobalProperties").getString("GlobalPropertiesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/GlobalProperties").getString("GlobalPropertiesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/GlobalProperties").getString("GlobalPropertiesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<GlobalProperties> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/GlobalProperties").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/GlobalProperties").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<GlobalProperties> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<GlobalProperties> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = GlobalProperties.class)
    public static class GlobalPropertiesControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GlobalPropertiesController controller = (GlobalPropertiesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "globalPropertiesController");
            return controller.getFacade().find(getKey(value));
        }

        com.bc.is.entity.GlobalPropertiesPK getKey(String value) {
            com.bc.is.entity.GlobalPropertiesPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.bc.is.entity.GlobalPropertiesPK();
            key.setSection(values[0]);
            key.setEntry(values[1]);
            return key;
        }

        String getStringKey(com.bc.is.entity.GlobalPropertiesPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getSection());
            sb.append(SEPARATOR);
            sb.append(value.getEntry());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof GlobalProperties) {
                GlobalProperties o = (GlobalProperties) object;
                return getStringKey(o.getGlobalPropertiesPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), GlobalProperties.class.getName()});
                return null;
            }
        }

    }

}
