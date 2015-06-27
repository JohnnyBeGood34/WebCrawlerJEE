/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import conf.Effectuer;
import conf.Search;
import conf.Searchresults;
import crawler.Crawler;
import crawler.Pool;
import crawler.SearchCrawler;
import crawler.Searcher;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import search.engine.api.GoogleSearch;
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
    @Inject
    SearchResultsManagedBean searchResultsManagedBean;
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

    public String getResultForSearch(Search idSearch)
      {
        searchResultsManagedBean.setSearchResults(idSearch);
        return "searchDetails?faces-redirect=true";
      }

    public void createSearch()
      {
        if (loginbean.getCurrentUser() != null)
          {
            Search searchFromDb = null;
            System.out.println("FUCKING THERM : " + this.search.getTherm());
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
                this.search.setDateSearch(new Date());

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
                searchResultsManagedBean.setSearchResults(searchFromDb);
              } else
              {
                /*try
                 {
                 //here need to lauch the scrapper
                 SearchCrawler searchCrawler = new SearchCrawler(this.search.getTherm(), this.search.getDeepLevel());
                 GoogleSearch googleEngine = new GoogleSearch(this.search.getTherm());
                 Pool pool = new Pool(searchCrawler, googleEngine);
                 //Get results
                 ArrayList<Searcher> searchers = new ArrayList<>();

                 for (int i = 1; i <= 2; i++)
                 {
                 Searcher searcher = new Searcher(pool);
                 searcher.start();
                 searchers.add(searcher);

                 }

                 for (Searcher searcher : searchers)
                 {
                 try
                 {
                 searcher.join();
                 } catch (InterruptedException ex)
                 {
                 Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 }
                 HashMap<String, ArrayList<String>> results;
                 results = searchCrawler.getMails();

                 System.out.println("");
                 System.out.println("");
                 for (Map.Entry<String, ArrayList<String>> entry : results.entrySet())
                 {

                 ArrayList<String> mails = entry.getValue();
                 System.out.println("Nombre de résultats pour le  site  " + entry.getKey() + "  : " + mails.size());

                 System.out.println("************Emails trouv�s***************");
                 for (String mail : mails)
                 {
                 System.out.println(mail);
                 }
                 System.out.println("");
                 System.out.println("");
                 }
                 //Insert results into db according to the search

                 //Populate resultSearch (display) searchResultsManagedBean.setSearchResults(this.search);
                    
                 } catch (IOException ex)
                 {
                 Logger.getLogger(SearchManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                 }*/
              }
          } else
          {
            //here need to lauch the scrapper and show results
            //Get results
            //Populate resultSearch (display)
          }

      }
  }
