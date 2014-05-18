package com.bc.is.controller;

import com.bc.is.services.DashFacade;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author bruno
 */
@ManagedBean(name = "dashController")
@SessionScoped
public class DashController {

    @EJB
    DashFacade ejbDash;
    
    private CartesianChartModel categoryModel; 

    public CartesianChartModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CartesianChartModel categoryModel) {
        this.categoryModel = categoryModel;
    }
    
    private int totalAsset;
    private List<String> asset;
    private List<String> assetType;
    private List<AssetType> assetTypeModel;
    private List<AssetData> assetDataModel;
    private HashMap<String, String> numAssetPerType;
    private HashMap<String, Date> assetByEndDate;
    public DashController() {
    }
    
    @PostConstruct
    public void init(){
        prepareView();
    }
    
    public String prepareView(){
        System.out.println("refresh");
      
        totalAsset  = ejbDash.getTotalAsset();
        assetByEndDate = ejbDash.getAssetByDueDate();
        asset = ejbDash.getAllAsset();
        assetType = ejbDash.getAllAssetType();
        numAssetPerType = ejbDash.getAssetByType();
        assetTypeModel = new ArrayList<AssetType>();
        for (Map.Entry<String, String> entry : numAssetPerType.entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            AssetType t = new AssetType();
            t.setAsset(key);
            t.setNumber(Integer.parseInt(val));
            assetTypeModel.add(t);
            
        }
        
        assetDataModel = new ArrayList<AssetData>();
        for (Map.Entry<String, Date> entry : assetByEndDate.entrySet()) {
            String key = entry.getKey();
            Date val = entry.getValue();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String ds = sdf.format(val);
            AssetData t = new AssetData();
            t.setAsset(key);
            t.setEndDate(ds);
            assetDataModel.add(t);
            
        }
        
        
        categoryModel = new CartesianChartModel();  
  
        ChartSeries asset = new ChartSeries();  
        asset.setLabel("Assets Types");  
        for (Iterator<AssetType> it = assetTypeModel.iterator(); it.hasNext();) {
            AssetType ca = it.next();
            asset.set(ca.Asset, ca.getNumber());
            
        }
     
  
        categoryModel.addSeries(asset);  
       
        
        
        return "/dash/List";
       
    }

    public List<AssetData> getAssetDataModel() {
        return assetDataModel;
    }
    
    

    public List<AssetType> getAssetTypeModel() {
        return assetTypeModel;
    }

    public void setAssetTypeModel(List<AssetType> assetTypeModel) {
        this.assetTypeModel = assetTypeModel;
    }

    public HashMap<String, Date> getAssetByEndDate() {
        return assetByEndDate;
    }

    
   

    public int getTotalAsset() {
        return totalAsset;
    }

    public void setTotalAsset(int totalAsset) {
        this.totalAsset = totalAsset;
    }

    public List<String> getAsset() {
        return asset;
    }

    public void setAsset(List<String> asset) {
        this.asset = asset;
    }

    public List<String> getAssetType() {
        return assetType;
    }

    public void setAssetType(List<String> assetType) {
        this.assetType = assetType;
    }

    public HashMap<String, String> getNumAssetPerType() {
        return numAssetPerType;
    }

    public void setNumAssetPerType(HashMap<String, String> numAssetPerType) {
        this.numAssetPerType = numAssetPerType;
    }
    
    public class AssetType{
        private int number;
        private String Asset;

        public AssetType() {
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getAsset() {
            return Asset;
        }

        public void setAsset(String Asset) {
            this.Asset = Asset;
        }
        
        
    }
    public class AssetData{
        String asset;
        String endDate;

        public String getAsset() {
            return asset;
        }

        public void setAsset(String asset) {
            this.asset = asset;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }
        
    }
    
}
