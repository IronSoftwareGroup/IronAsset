package com.bc.is.services;

import com.bc.is.entity.GlobalProperties;
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
public class GlobalPropertiesFacade extends AbstractFacade<GlobalProperties> {
    @PersistenceContext(unitName = "com.bc.is_IronAsset_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GlobalPropertiesFacade() {
        super(GlobalProperties.class);
    }
    public List<GlobalProperties> getPropertiesBySection(String section){
        Query q = em.createNamedQuery("GlobalProperties.findBySection");
        q.setParameter("section", section);
        return q.getResultList();
    }
    public String getPropertiesValue(String section, String entry){
        Query q = em.createNamedQuery("GlobalProperties.findValue");
        q.setParameter("section", section);
         q.setParameter("entry", entry);
        
         GlobalProperties gp = (GlobalProperties)q.getSingleResult();
        
         return gp.getValue();
    }
    
}
