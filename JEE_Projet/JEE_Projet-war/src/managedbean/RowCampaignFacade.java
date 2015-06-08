/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import conf.RowCampaign;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JOHN
 */
@Stateless
public class RowCampaignFacade extends AbstractFacade<RowCampaign> implements RowCampaignFacadeLocal
  {
    @PersistenceContext(unitName = "JEE_Projet-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
      {
        return em;
      }

    public RowCampaignFacade()
      {
        super(RowCampaign.class);
      }
    
  }
