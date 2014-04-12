package com.bc.is.controller;

import com.bc.is.entity.Reminder;
import com.bc.is.controller.util.JsfUtil;
import com.bc.is.controller.util.JsfUtil.PersistAction;
import com.bc.is.services.ReminderFacade;

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

@ManagedBean(name = "reminderController")
@SessionScoped
public class ReminderController implements Serializable {

    @EJB
    private com.bc.is.services.ReminderFacade ejbFacade;
    private List<Reminder> items = null;
    private Reminder selected;
    private int assetID;

    public ReminderController() {
    }

    public Reminder getSelected() {
        return selected;
    }

    public void setSelected(Reminder selected) {
        this.selected = selected;
    }

    public int getAssetID() {
        return assetID;
    }

    public void setAssetID(int assetID) {
        this.assetID = assetID;
    }

    
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setReminderPK(new com.bc.is.entity.ReminderPK());
    }

    private ReminderFacade getFacade() {
        return ejbFacade;
    }

    public Reminder prepareCreate() {
        selected = new Reminder();
        initializeEmbeddableKey();
        return selected;
    }
    public void prepareCreateByAsset(){
        System.out.println("create by asset:"+assetID);
        prepareCreate();
        selected.getReminderPK().setAssetId(assetID);
        selected.setSent("NO");
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Reminder").getString("ReminderCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Reminder").getString("ReminderUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Reminder").getString("ReminderDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Reminder> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    public String getReminderByAsset(int assetId) {
        System.out.println("asset id:" + assetId);
        this.assetID=assetId;
            items = getFacade().getByAssetId(assetId);
        return "/reminder/List";
     
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Reminder").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Reminder").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Reminder> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Reminder> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Reminder.class)
    public static class ReminderControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReminderController controller = (ReminderController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "reminderController");
            return controller.getFacade().find(getKey(value));
        }

        com.bc.is.entity.ReminderPK getKey(String value) {
            com.bc.is.entity.ReminderPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.bc.is.entity.ReminderPK();
            key.setAssetId(Integer.parseInt(values[0]));
            key.setSequence(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.bc.is.entity.ReminderPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getAssetId());
            sb.append(SEPARATOR);
            sb.append(value.getSequence());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Reminder) {
                Reminder o = (Reminder) object;
                return getStringKey(o.getReminderPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Reminder.class.getName()});
                return null;
            }
        }

    }

}
