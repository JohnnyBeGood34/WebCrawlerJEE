/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import conf.Effectuer;
import conf.Search;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author JOHN
 */
@Named(value = "searchManagedSessionbean")
@SessionScoped
public class SearchManagedSessionbean implements Serializable
  {
    private List<Search> searches;
    private List<Effectuer> userSearches;
    /**
     * Creates a new instance of SearchManagedSessionbean
     */
    public SearchManagedSessionbean()
      {
      }

    public String getDateForSearch(Integer pointer){
        return userSearches.get(pointer).getDateSearch().toString();
    }
    
    public List<Search> getSearches()
      {
        return searches;
      }

    public void setSearches(List<Search> searches)
      {
        this.searches = searches;
      }

    public List<Effectuer> getUserSearches()
      {
        return userSearches;
      }

    public void setUserSearches(List<Effectuer> userSearches)
      {
        this.userSearches = userSearches;
      }
    
    
  }
