/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import conf.Effectuer;
import conf.Search;
import conf.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Stef
 */
@Stateless
@LocalBean
public class SearchManager {
    @PersistenceContext(unitName = "JEE_Projet-ejbPU")
    private EntityManager em;

    public void createSearch(Search aSearch){
        em.persist(aSearch);
    }
    
    public boolean testSearchExistanceByTherm(String therm){
        Query request = em.createNamedQuery("Search.findByTherm");
        request.setParameter("therm",therm);
        return request.getResultList().size() == 1;
    }
    
    public Search getSearchForTherm(String therm){
        Query request = em.createNamedQuery("Search.findByTherm");
        request.setParameter("therm",therm);
        return (Search) request.getSingleResult();
    }
    
    public void createUserSearch(Effectuer userSearch){
        em.persist(userSearch);
    }
    public void persist(Object object) {
        em.persist(object);
    }

    public List<Search> getAllSearch(){
        Query request = em.createNamedQuery("Search.findAll");
        return request.getResultList();
    }

    public List<Effectuer> getAllUserSearches(User currentUser){
        Query request = em.createNamedQuery("Effectuer.findByUserId");
        request.setParameter("idUser", currentUser);
        return (ArrayList<Effectuer>) request.getResultList();
    }
    
    public List<Search> getAllSearchForUser(User currentUser)
      {
        ArrayList<Effectuer> userSearches = new ArrayList<>();
        ArrayList<Search> searches = new ArrayList<>();
        Query request = em.createNamedQuery("Effectuer.findByUserId");
        request.setParameter("idUser", currentUser);
        userSearches = (ArrayList<Effectuer>) request.getResultList();
        for(Effectuer userSearch : userSearches){
            Query userSearchRequest = em.createNamedQuery("Search.findByIdSearch");
            userSearchRequest.setParameter("idSearch",userSearch.getIdSearch());
            searches.add((Search)userSearchRequest.getSingleResult());
        }
        return searches;
      }
    
}
