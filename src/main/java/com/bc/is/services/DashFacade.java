/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bc.is.services;

import com.bc.is.entity.Asset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bruno
 */
@Stateless
public class DashFacade {
    
    @EJB
    private AssetFacade assetFacade;
    
    private int totalAsset=0;
    private List<String> allAsset= new ArrayList<String>();
    private List<String> allAssetType = new ArrayList<String>();
    private HashMap<String, String> assetByType = new HashMap<String,String>();

    public DashFacade() {
    }
    
    @PostConstruct
    private void init(){
        totalAsset=this.getTotalAssets();
        allAsset = this.getAsset();
        allAssetType=this.listAllAssetType();
        assetByType = this.countAssetByType();
    }
    private int getTotalAssets(){
        return assetFacade.count();        
    }
 

    public int getTotalAsset() {
        return totalAsset;
    }

  

    public List<String> getAllAsset() {
        return allAsset;
    }

 

    public List<String> getAllAssetType() {
        return allAssetType;
    }


    
    

    public HashMap<String, String> getAssetByType() {
        return assetByType;
    }

  
    
    
   
    private List<String> getAsset(){
        List<Asset> all = assetFacade.findAll();
        List <String> result = new ArrayList<String>();
        for (Iterator<Asset> it = all.iterator(); it.hasNext();) {
            Asset asset = it.next();
            result.add(asset.getName());
        }
        return result;
    }
    
    private List<String> listAllAssetType(){
        List<Asset> all = assetFacade.findAll();
        List <String> result = new ArrayList<String>();
        for (Iterator<Asset> it = all.iterator(); it.hasNext();) {
            Asset asset = it.next();
            if(!result.contains(asset.getType())){
                result.add(asset.getType());
            }
        }
        return result;
    }

    private HashMap<String, String> countAssetByType() {
        HashMap<String, String> m = new HashMap<String, String>();
        for (Iterator<String> it = allAssetType.iterator(); it.hasNext();) {
            String string = it.next();
            int c = assetFacade.countByType(string);
            String cs = String.valueOf(c);
            m.put(string, cs);
            
            
        }
       return m;
    }
    
    
    
}
