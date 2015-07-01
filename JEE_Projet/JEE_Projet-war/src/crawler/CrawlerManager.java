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
 *
 * @author Kevin
 */
public class CrawlerManager {

    private final String _inputSearch;
    private final int _deepLevel;
    private final int NB_THREADS = 8;
    private final int LIMIT;
    
    public CrawlerManager(String inputSearch, int deepLevel,int limit) {
        this._inputSearch = inputSearch;
        this._deepLevel = deepLevel;
        LIMIT = limit;
    }

    
    public HashMap<String, ArrayList<String>> getResults() throws IOException{
        SearchCrawler search = new SearchCrawler(_inputSearch, _deepLevel);
        search.setLimit(LIMIT);
        GoogleSearch googleEngine = new GoogleSearch(_inputSearch);//Le moteur de recherche � utiliser
        Pool pool = new Pool(googleEngine);//Construction du pool d'URLs � partir des r�sultats du moteur de recherche

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
