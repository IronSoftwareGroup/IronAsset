package com.bc.is.controller;

import com.bc.is.entity.Asset;
import com.bc.is.entity.Lov;
import com.bc.is.jsf.util.JsfUtil;
import com.bc.is.jsf.util.JsfUtil.PersistAction;
import com.bc.is.services.AssetFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.datatable.DataTable;

@ManagedBean(name = "assetController")
@SessionScoped
public class AssetController implements Serializable {

    @EJB
    private com.bc.is.services.AssetFacade ejbFacade;
     @EJB
    private com.bc.is.services.LovFacade ejbLov;
    private List<Asset> items = null;
    private List<Asset> filteredAsset=null;

    public List<Asset> getFilteredAsset() {
        return filteredAsset;
    }

    public void setFilteredAsset(List<Asset> filteredAsset) {
        this.filteredAsset = filteredAsset;
    }
    private Asset selected;

    public AssetController() {
    }

    public Asset getSelected() {
        return selected;
    }

    public void setSelected(Asset selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
    public List<String> listOfVale(String subject){
        List<String> r = new ArrayList<String>();
        List<Lov> lov= ejbLov.getEntryBySubject(subject);
        for (Iterator<Lov> it = lov.iterator(); it.hasNext();) {
            Lov lov1 = it.next();
            r.add(lov1.getLovPK().getEntry());
            
        }
        return  r;
    }
    
    public void copy(){
        Asset newAsset = new Asset();
        newAsset.setCurrency(selected.getCurrency());
        newAsset.setDescription(selected.getDescription());
        newAsset.setPriceMetric(selected.getPriceMetric());
        newAsset.setUom(selected.getUom());
        newAsset.setType(selected.getType());
        newAsset.setType2(selected.getType2());
        newAsset.setType3(selected.getType3());
        newAsset.setVendorCompany(selected.getVendorCompany());
        newAsset.setVendorEmail(selected.getVendorEmail());
        newAsset.setVendorName(selected.getVendorName());
        newAsset.setVendorPhone(selected.getVendorPhone());
        newAsset.setVendorWebsite(selected.getVendorWebsite());
        
       
        
        selected = new Asset();
        selected.setCurrency(newAsset.getCurrency());
        selected.setPriceMetric(newAsset.getPriceMetric());
        selected.setUom(newAsset.getUom());
        selected.setType(newAsset.getType());
        selected.setType2(newAsset.getType2());
        selected.setType3(newAsset.getType3());
        selected.setVendorCompany(newAsset.getVendorCompany());
        selected.setVendorEmail(newAsset.getVendorEmail());
        selected.setVendorName(newAsset.getVendorName());
        selected.setVendorPhone(newAsset.getVendorPhone());
        selected.setVendorWebsite(newAsset.getVendorWebsite());
        selected.setSentReminder(0);
        
        newAsset=null;
       
        
    }
    
    public void refresh(){
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
            .findComponent("AssetListForm:datalist");
    if (dataTable != null) {
        dataTable.reset();
    }
        items=null;
    }

    private AssetFacade getFacade() {
        return ejbFacade;
    }

    public Asset prepareCreate() {
        selected = new Asset();
        selected.setSentReminder(0);
        selected.setStartDate(GregorianCalendar.getInstance().getTime());
        selected.setRequestDate(GregorianCalendar.getInstance().getTime());
       
        selected.setTotReminder(0);
        
       // selected.setId(null);
        initializeEmbeddableKey();
        return selected;
    }
    

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AssetCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AssetUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AssetDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Asset> getItems() {
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

    public List<Asset> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Asset> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Asset.class)
    public static class AssetControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AssetController controller = (AssetController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "assetController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Asset) {
                Asset o = (Asset) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Asset.class.getName()});
                return null;
            }
        }

    }

}
