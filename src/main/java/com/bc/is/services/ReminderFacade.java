/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bc.is.services;

import com.bc.is.entity.Reminder;
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
public class ReminderFacade extends AbstractFacade<Reminder> {
    @PersistenceContext(unitName = "com.bc.is_IronAsset_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReminderFacade() {
        super(Reminder.class);
    }
    
    public List<Reminder> getByAssetId(int id){
        Query q = em.createNamedQuery("Reminder.findByAssetId");
        q.setParameter("assetId", id);
        return q.getResultList();
    }

   public  List<Reminder> getActive() {
     Query q = em.createNamedQuery("Reminder.findBySent");
        q.setParameter("sent", "NO");
        return q.getResultList();}
    
}
