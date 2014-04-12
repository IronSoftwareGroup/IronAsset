/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    
}
