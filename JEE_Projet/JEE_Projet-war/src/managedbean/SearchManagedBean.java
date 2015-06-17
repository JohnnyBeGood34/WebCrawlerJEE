/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import conf.Effectuer;
import conf.Search;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import session.SearchManager;

/**
 *
 * @author JOHN
 */
@Named(value = "searchManagedBean")
@RequestScoped
public class SearchManagedBean
  {

    private Search search;
    private Effectuer userSearch;
    @Inject
    LoginManagedBean loginbean;
    @Inject
    SearchManagedSessionbean searchManagedSessionBean;
    @EJB
    private SearchManager searchManager;

    public void getUserSearchArray(){
        searchManagedSessionBean.setSearches(searchManager.getAllSearchForUser(loginbean.getCurrentUser()));
        searchManagedSessionBean.setUserSearches(searchManager.getAllUserSearches(loginbean.getCurrentUser()));
        
    }
    
    public SearchManager getSearchManager()
      {
        return searchManager;
      }

    public void setSearchManager(SearchManager searchManager)
      {
        this.searchManager = searchManager;
      }

    public Search getSearch()
      {
        return search;
      }

    public void setSearch(Search search)
      {
        this.search = search;
      }

    /**
     * Creates a new instance of SearchManagedBean
     */
    public SearchManagedBean()
      {
        this.search = new Search();
        this.searchManager = new SearchManager();
      }

    public void createSearch()
      {
        Search searchFromDb = null;
        boolean searchExists = searchManager.testSearchExistanceByTherm(this.search.getTherm());
        if (searchExists)
          {
            searchFromDb = searchManager.getSearchForTherm(this.search.getTherm());
          } else
          {
            //Create
            searchManager.createSearch(this.search);
            System.out.println("SEARCH ID : " + this.search.getIdSearch());
            //populate Effectuer
            userSearch.setDateSearch(new Date());
            userSearch.setIdSearch(this.search);
            userSearch.setIdUser(loginbean.getCurrentUser());
            //Create Effectuer
            searchManager.createUserSearch(userSearch);
          }
        //Here need to get results from database according to the date
        if (searchFromDb != null)
          {

          } else
          {
            //here need to lauch the scrapper
          }

      }
  }
