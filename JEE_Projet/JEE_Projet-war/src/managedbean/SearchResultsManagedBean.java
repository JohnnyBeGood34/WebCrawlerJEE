/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import conf.Search;
import conf.Searchresults;
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

    public void updateIsInCampaign(Searchresults searchResult){
        searchManager.updateIsInCampaign(searchResult);
    }
    
    public void updateAllIsInCampaign(){
        
    }
    
    public List<Searchresults> getSearchResults()
      {
        this.searchResults = searchManager.getResultsFromDataBase(searchSessionbean.getSearch());
          System.out.println("FUCKING SEARCH RESULT : "+this.searchResults.toString());
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
