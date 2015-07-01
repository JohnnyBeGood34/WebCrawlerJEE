/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import conf.Effectuer;
import conf.Search;
import conf.Searchresults;
import conf.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class SearchManager
  {

    @PersistenceContext(unitName = "JEE_Projet-ejbPU")
    private EntityManager em;

    public void createSearch(Search aSearch)
      {
        em.persist(aSearch);
      }

    public String getDateFromSearchId(Search aSearch)
      {
        Query request = em.createNamedQuery("Effectuer.findByDateSearch");
        request.setParameter("idSearch", aSearch);
        String dateToRetreive = (String) request.getSingleResult();
        return dateToRetreive;
      }

    public boolean testSearchExistance(String therm, Integer deeplevel)
      {
        Query request = em.createNamedQuery("Search.findByThermAndDeepLevel");
        request.setParameter("therm", therm);
        request.setParameter("deepLevel", deeplevel);
        return request.getResultList().size() >0;
      }

    public boolean isSearchAvailable(Search aSearch)
      {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(new Date());
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(aSearch.getDateSearch());
        
        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        
        if(diffMonth >= 1){
            return false;
        }
        return true;
      }

    public Search getSearchForTherm(String therm)
      {
        Query request = em.createNamedQuery("Search.findByTherm");
        request.setParameter("therm", therm);
        return (Search) request.getSingleResult();
      }

    public void createUserSearch(Effectuer userSearch)
      {
        em.persist(userSearch);
      }

    public void persist(Object object)
      {
        em.persist(object);
      }

    public List<Search> getAllSearch()
      {
        Query request = em.createNamedQuery("Search.findAll");
        return request.getResultList();
      }

    public ArrayList<Effectuer> getAllUserSearches(User currentUser)
      {
        ArrayList<Effectuer> effectuerArrayList = new ArrayList<Effectuer>();
        Query request = em.createNamedQuery("Effectuer.findByUserId");
        request.setParameter("idUser", currentUser);
        for (Effectuer userSearch : (List<Effectuer>) request.getResultList())
          {
            effectuerArrayList.add(userSearch);
          }
        return effectuerArrayList;
      }

    public ArrayList<Search> getAllSearchForUser(User currentUser)
      {
        ArrayList<Search> searches = new ArrayList<>();
        Query request = em.createNamedQuery("Effectuer.findByUserId");
        request.setParameter("idUser", currentUser);
        for (Effectuer userSearch : (List<Effectuer>) request.getResultList())
          {
            Query userSearchRequest = em.createNamedQuery("Search.findByIdSearch");
            userSearchRequest.setParameter("idSearch", userSearch.getIdSearch().getIdSearch());
            searches.add((Search) userSearchRequest.getSingleResult());
          }
        return searches;
      }

    public List<Searchresults> getResultsFromDataBase(Search search)
      {
        Query request = em.createNamedQuery("Searchresults.findByIdSearch");

        request.setParameter("idSearch", search);
        return request.getResultList();
      }

    public void updateIsInCampaign(Searchresults searchResult)
      {
        if (searchResult.getIsInCampaign())
          {
            searchResult.setIsInCampaign(false);
          } else
          {
            searchResult.setIsInCampaign(true);
          }
        em.merge(searchResult);
      }

  }
