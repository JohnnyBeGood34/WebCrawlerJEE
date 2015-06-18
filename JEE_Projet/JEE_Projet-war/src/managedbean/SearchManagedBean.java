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

    private List<Search> searchList;
    private Search search;
    private Effectuer userSearch;
    @Inject
    LoginManagedBean loginbean;
    @Inject
    UserSearchManagedSessionBean userSearchSessionBean;
    @EJB
    private SearchManager searchManager;
    private String deepLevel;

    public String getDeepLevel()
      {
        return this.deepLevel;
      }

    public void setDeepLevel(String level)
      {
        this.deepLevel = level;
      }

    public List<Search> getSearchlist()
      {
        return searchManager.getAllSearchForUser(loginbean.getCurrentUser());
      }

    public void setSearchList(List<Search> searches)
      {
        searchList = searches;
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
        this.userSearch = new Effectuer();
        this.deepLevel = null;
      }

    public void createSearch()
      {
        if (loginbean.getCurrentUser() != null)
          {
            Search searchFromDb = null;
            boolean searchExists = searchManager.testSearchExistanceByTherm(this.search.getTherm());
            if (searchExists)
              {
                searchFromDb = searchManager.getSearchForTherm(this.search.getTherm());
              } else
              {
                Integer deeplevelinteger = 1;
                if (deepLevel != null)
                  {
                    switch (deepLevel)
                      {
                        case "2":
                            deeplevelinteger = 2;
                            break;
                        case "3":
                            deeplevelinteger = 3;
                            break;
                        case "4":
                            deeplevelinteger = 4;
                            break;
                        case "5":
                            deeplevelinteger = 5;
                            break;
                      }
                  }
                this.search.setDeepLevel(deeplevelinteger);
                if (this.search.getIsFr() == null)
                  {
                    this.search.setIsFr(false);
                  }

                //Create
                searchManager.createSearch(this.search);

                //populate Effectuer
                if (loginbean.getCurrentUser() != null)
                  {
                    userSearch.setDateSearch(new Date());
                    userSearch.setIdSearch(this.search);
                    userSearch.setIdUser(loginbean.getCurrentUser());
                    //Create Effectuer
                    searchManager.createUserSearch(userSearch);
                  }
              }
            //Here need to get results from database according to the date
            if (searchFromDb != null)
              {
                
              } else
              {
                //here need to lauch the scrapper
              }
          } else
          {
            //here need to lauch the scrapper and show results
          }

      }
  }
