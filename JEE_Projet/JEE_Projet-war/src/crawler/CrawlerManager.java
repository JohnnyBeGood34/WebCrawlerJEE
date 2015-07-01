/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import search.engine.api.GoogleSearch;

/**
 * Class encapsulating and managing the threads for the crawler
 * @author Kevin
 */
public class CrawlerManager {
    
    /**
     * The keyword or several keywords the user is looking for
     */
    private final String _inputSearch;
    /**
     * Depth level of the search
     */
    private final int _deepLevel;
    /**
     * The number of threads to launch
     */
    private  final int NB_THREADS = 8;
    /**
     * The maximum number of emails we want to find 
     */
    private final int LIMIT;
    
    /**
     * 
     * @param inputSearch
     * @param deepLevel
     * @param limit 
     */
    public CrawlerManager(String inputSearch, int deepLevel,int limit) {
        this._inputSearch = inputSearch;
        this._deepLevel = deepLevel;
        LIMIT = limit;
    }


    /**
     * 
     * @return A dictionary with the website as key 
     * and a list of emails as value
     * @throws IOException 
     */
                               
    public HashMap<String, ArrayList<String>> getResults() throws IOException{

        SearchCrawler search = new SearchCrawler(_inputSearch, _deepLevel);
        search.setLimit(LIMIT);
        GoogleSearch googleEngine = new GoogleSearch(_inputSearch);
        Pool pool = new Pool(googleEngine);

        ArrayList<Searcher> searchers = new ArrayList<>();

        for (int i = 1; i <= NB_THREADS; i++) {
            Searcher searcher = new Searcher(pool,search);
            
            searcher.start();
            searchers.add(searcher);
        }

        for (Searcher searcher : searchers) {
            
            try {
                searcher.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(CrawlerManager.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }        
        HashMap<String, ArrayList<String>> results;
        results = search.getMails();
        return results;
    }
    
    /**
     * 
     * @return The list of all emails
     * @throws IOException 
     */
    public List<String> getResultFromSearchCrawler() throws IOException {
        
        List<String> resultListEmailsFromSearch = new ArrayList<>();
        HashMap<String, ArrayList<String>> results = getResults();
        
        for (Map.Entry<String, ArrayList<String>> entry : results.entrySet()) {

            ArrayList<String> mails = entry.getValue();
            for (String mail : mails) {
                resultListEmailsFromSearch.add(mail);
            }
        }
        return resultListEmailsFromSearch;
    }

}
