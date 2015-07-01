
package crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import search.engine.api.SearchEngine;

/**
 * Class representing the list of all URLs found from the search 
 * @author Stef
 */
public class Pool {
    
    /**
     * List of URLs found from the search and not processed yet
     */
    private final ArrayList<ResultSearch> results;
    
    /**
     * List of URLs found and already processed
     */
    private final ArrayList<String> urlsAlreadyTreated;
    
    /**
     * Object of implementing the Interface SearchEngine and representing 
     * the type of engine making the search
     */
    private SearchEngine searchEngine;
    
    final Lock lock = new ReentrantLock();
    final Condition urlAdded = lock.newCondition();
   
    
    /**
     * Constructs a new Pool 
     * 
     * @param engine An object implementing the SearchEngine interface
     * @throws IOException 
     */
    public Pool(SearchEngine engine) throws IOException{
        this.searchEngine =  engine;
        results = searchEngine.findUrls();
        System.out.println("********************");
        for(ResultSearch result : results) System.out.println("URL from "+ engine.getNameEngine()+ ": "+result.getUrl());
         System.out.println("********************");
        urlsAlreadyTreated = new ArrayList();
        for(ResultSearch result:results) urlsAlreadyTreated.add(result.getUrl());
        
    }
    
    /**
     * Method which adds an URL to the list 
     * @param result a ResultSearch
     * thread-safe method
     */
    public void addUrl(ResultSearch result){
        lock.lock();
        String url = result.getUrl().trim();
        if(!urlsAlreadyTreated.contains(url)){
            System.out.println("Adding link to pool : "+url);
            results.add(result);
            urlAdded.signal();
        }
        
       lock.unlock();
        
    }
    
   
    /**
     * Method allowing to change of search engine used to 
     * make the search (i.e : Google,Yahoo,etc..)
     * @param engine 
     */
    public void setSearchEngine(SearchEngine engine){
        this.searchEngine=engine;
    }
    
    /**
     * 
     * @return List of result objects
     */
    public ArrayList<ResultSearch> getUrls(){
        return results;
    }
    
    /**
     * Thread-safe method allowing to get an URL from the list if the list 
     * is not empty
     * @return String An URL from the list
     */
    public synchronized ResultSearch tryGetUrl() {
                
        ResultSearch result = null;
                    
            if(!results.isEmpty()){
                result = results.get(0);
                results.remove(0);
                urlsAlreadyTreated.add(result.getUrl());
            }
                 
        return result;
    }
    
    /**
     * Method to know if an url has already been processed
     * for the current search
     * @param url The string url
     * @return True if the url has already  been processed , false otherwise
     */
    public synchronized boolean hasAlreadyTreatedUrl(String url){
        return urlsAlreadyTreated.contains(url);
    }
    

}
