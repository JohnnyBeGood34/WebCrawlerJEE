/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import conf.Searchresults;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JOHN
 */
@Stateless
public class SearchresultsFacade extends AbstractFacade<Searchresults> implements SearchresultsFacadeLocal
  {
    @PersistenceContext(unitName = "JEE_Projet-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
      {
        return em;
      }

    public SearchresultsFacade()
      {
        super(Searchresults.class);
      }
    
  }
