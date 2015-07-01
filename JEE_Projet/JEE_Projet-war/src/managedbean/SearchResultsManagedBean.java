/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import conf.Search;
import conf.Searchresults;
import crawler.CrawlerManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import session.SearchManager;

/**
 *
 * @author JOHN
 */
@Named(value = "searchResultsManagedBean")
@RequestScoped
public class SearchResultsManagedBean
  {

    private boolean searchAlreadyLauched = false;
    private List<Searchresults> searchResults;
    @EJB
    private SearchManager searchManager;
    @Inject
    SearchManagedSessionBean searchSessionbean;
    @Inject
    LoginManagedBean loginbean;

    /**
     * Creates a new instance of SearchResultsManagedBean
     */
    public SearchResultsManagedBean()
      {
      }

    public void updateIsInCampaign(Searchresults searchResult)
      {
        searchManager.updateIsInCampaign(searchResult);
      }

    public void updateAllIsInCampaign()
      {
        for (Searchresults result : this.searchResults)
          {
            searchManager.updateIsInCampaign(result);
          }
      }

    public List<Searchresults> getSearchResults()
      {
        this.searchResults = searchManager.getResultsFromDataBase(searchSessionbean.getSearch());
        return this.searchResults;
      }

    public void setSearchResults(List<Searchresults> aSearch)
      {
        this.searchResults = aSearch;
      }

    public List<Searchresults> getLocalSearchResults()
      {
        return this.searchResults;
      }
  }
