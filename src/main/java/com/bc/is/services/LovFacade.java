package com.bc.is.services;

import com.bc.is.entity.Lov;
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
public class LovFacade extends AbstractFacade<Lov> {
    @PersistenceContext(unitName = "com.bc.is_IronAsset_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LovFacade() {
        super(Lov.class);
    }
    public List<Lov> getEntryBySubject(String subject){
        Query q = em.createNamedQuery("Lov.findBySubject");
        q.setParameter("subject", subject);
        return q.getResultList();
    }
    
}
