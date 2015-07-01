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

    private List<Searchresults> searchResults;
    @EJB
    private SearchManager searchManager;
    @Inject
    SearchManagedSessionBean searchSessionbean;

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
        List<Searchresults> listToReturnIfResultsAreEmpty = new ArrayList();
        this.searchResults = searchManager.getResultsFromDataBase(searchSessionbean.getSearch());
        //If there aren't any results in database
        if (this.searchResults.isEmpty() && searchSessionbean.getSearch()!= null)
          {
            try
              {
                int limit = 10;
                CrawlerManager crawlerManager = new CrawlerManager(searchSessionbean.getSearch().getTherm(), searchSessionbean.getSearch().getDeepLevel(), limit);
                HashMap<String, ArrayList<String>> results = crawlerManager.getResults();

                for (Map.Entry<String, ArrayList<String>> entry : results.entrySet())
                  {

                    ArrayList<String> mails = entry.getValue();
                    for (String mail : mails)
                      {
                        //Create results
                        Searchresults searchResult = new Searchresults();
                        searchResult.setEmailResult(mail);
                        searchResult.setIdSearch(searchSessionbean.getSearch());
                        searchResult.setSiteFound(entry.getKey());
                        listToReturnIfResultsAreEmpty.add(searchResult);
                      }
                  }

              } catch (IOException ex)
              {
                Logger.getLogger(SearchManagedBean.class.getName()).log(Level.SEVERE, null, ex);
              }
            this.searchResults = listToReturnIfResultsAreEmpty;
          }
        return this.searchResults;
      }

    public void setSearchResults(Search aSearch)
      {
        searchSessionbean.setSearch(aSearch);
      }

    public List<Searchresults> getLocalSearchResults()
      {
        return this.searchResults;
      }
  }
