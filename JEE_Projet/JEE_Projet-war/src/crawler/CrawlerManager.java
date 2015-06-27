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

    public CrawlerManager(String inputSearch, int deepLevel) {
        this._inputSearch = inputSearch;
        this._deepLevel = deepLevel;
    }

    public List<String> getResultFromSearchCrawler() throws IOException {
        List<String> resultListEmailsFromSearch = new ArrayList<>();
        
        int nbThreads = 2;//Nombre de threads � utiliser   

        SearchCrawler search = new SearchCrawler(_inputSearch, _deepLevel);
        GoogleSearch googleEngine = new GoogleSearch(_inputSearch);//Le moteur de recherche � utiliser
        Pool pool = new Pool(search, googleEngine);//Construction du pool d'URLs � partir des r�sultats du moteur de recherche

        ArrayList<Searcher> searchers = new ArrayList<>();

        for (int i = 1; i <= nbThreads; i++) {
            Searcher searcher = new Searcher(pool);
            searcher.start();
            searchers.add(searcher);
        }

        for (Searcher searcher : searchers) {
            try {
                searcher.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        HashMap<String, ArrayList<String>> results;
        results = search.getMails();

        System.out.println("");
        System.out.println("");
        for (Map.Entry<String, ArrayList<String>> entry : results.entrySet()) {

            ArrayList<String> mails = entry.getValue();
            for (String mail : mails) {
                resultListEmailsFromSearch.add(mail);
            }
        }

        return resultListEmailsFromSearch;
    }

}
