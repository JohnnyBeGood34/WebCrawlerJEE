/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import conf.Search;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author JOHN
 */
@Named(value = "searchManagedSessionBean")
@SessionScoped
public class SearchManagedSessionBean implements Serializable
  {
    private Search search;
    /**
     * Creates a new instance of SearchManagedSessionBean
     */
    public SearchManagedSessionBean()
      {
      }
    public Search getSearch(){
        return this.search;
    }
    
    public void setSearch(Search search){
        this.search = search;
    }
  }
