package com.bc.is.entity;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author bruno
 */
public class Dash {
    private int totalAsset;
    private List<String> asset;
    private List<String> assetType;
    private HashMap<String, String> numAssetPerType;

    public Dash() {
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
    
    
}
