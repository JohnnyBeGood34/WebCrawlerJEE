/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import conf.Effectuer;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Kevin
 */
@Stateless
@LocalBean
public class EffectuerManager {
    @PersistenceContext(unitName = "JEE_Projet-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

   public List<Effectuer> getAllEffectuer(){
        Query request = em.createNamedQuery("Effectuer.findAll");
        return request.getResultList();
    }
    
    
}
