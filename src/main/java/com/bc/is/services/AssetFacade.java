package com.bc.is.services;

import com.bc.is.entity.Asset;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author bruno
 */
@Stateless
public class AssetFacade extends AbstractFacade<Asset> {
    @PersistenceContext(unitName = "com.bc.is_IronAsset_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssetFacade() {
        super(Asset.class);
    }
    
    public int countByType(String type){
        Query q = em.createNamedQuery("Asset.countByType");
        q.setParameter("type", type);
        
        return ((Long) q.getSingleResult()).intValue();
        
    }

    public List<Asset> findAllByDate() {
     Query q = em.createNamedQuery("Asset.findAllByDate");
        
        
        return q.getResultList();
    }
    
}
