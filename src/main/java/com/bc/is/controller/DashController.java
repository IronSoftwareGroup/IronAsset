package com.bc.is.controller;

import com.bc.is.services.DashFacade;
import java.util.ArrayList;
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
    private HashMap<String, String> numAssetPerType;
    public DashController() {
    }
    
    @PostConstruct
    public void init(){
        prepareView();
    }
    
    public String prepareView(){
        System.out.println("refresh");
        
        totalAsset  = ejbDash.getTotalAsset();
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

    public List<AssetType> getAssetTypeModel() {
        return assetTypeModel;
    }

    public void setAssetTypeModel(List<AssetType> assetTypeModel) {
        this.assetTypeModel = assetTypeModel;
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
    
}
