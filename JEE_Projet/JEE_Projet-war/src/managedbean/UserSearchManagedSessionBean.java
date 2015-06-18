/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import conf.Effectuer;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author JOHN
 */
@Named(value = "userSearchManagedSessionBean")
@SessionScoped
public class UserSearchManagedSessionBean implements Serializable
  {
    Effectuer userSearch;

    public Effectuer getUserSearch()
      {
        return userSearch;
      }

    public void setUserSearch(Effectuer userSearch)
      {
        this.userSearch = userSearch;
      }
    
    public String getDate(){
        return userSearch.getDateSearch().toString();
    }
    
    /**
     * Creates a new instance of UserSearchManagedSessionBean
     */
    public UserSearchManagedSessionBean()
      {
      }
    
  }
