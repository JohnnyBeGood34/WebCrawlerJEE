/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import conf.Searchresults;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author JOHN
 */
@Local
public interface SearchresultsFacadeLocal
  {

    void create(Searchresults searchresults);

    void edit(Searchresults searchresults);

    void remove(Searchresults searchresults);

    Searchresults find(Object id);

    List<Searchresults> findAll();

    List<Searchresults> findRange(int[] range);

    int count();
    
  }
