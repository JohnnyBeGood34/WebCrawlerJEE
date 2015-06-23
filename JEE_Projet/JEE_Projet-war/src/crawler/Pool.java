
package crawler;

import search.engine.api.GoogleSearch;
import java.io.IOException;
import java.util.ArrayList;
import search.engine.api.SearchEngine;

/**
 * Class representing the list of all URLs found from the search 
 * @author Stef
 */
public class Pool {
    
    /**
     * List of URLs found from the search and not processed yet
     */
    private ArrayList<String> listeUrls;
    
    /**
     * List of URLs found and already processed
     */
    private ArrayList<String> urlsAlreadyTreated;
    
    /**
     * Object of implementing the Interface SearchEngine and representing 
     * the type of engine making the search
     */
    private SearchEngine searchEngine;
    
    /**
     * Object representing a search made by the user
     */
    private final SearchCrawler search;
    
    /**
     * Constructor 
     * @param search A search with keyword and level search
     * @throws IOException 
     */
    public Pool(SearchCrawler search,SearchEngine engine) throws IOException{
        this.searchEngine =  engine;
        listeUrls = searchEngine.findUrls();
        System.out.println("********************");
        for(String url:listeUrls) System.out.println("URL from Google : "+url);
         System.out.println("********************");
        urlsAlreadyTreated = new ArrayList();
        this.search=search;
    }
    
    /**
     * Method which adds an URL to the list 
     * @param url 
     */
    public void addUrl(String url){
        if(!urlsAlreadyTreated.contains(url)){
            System.out.println("Adding link to pool : "+url);
            listeUrls.add(url);
        }
        
    }
    
   
    /**
     * Method allowing to change of search engine used to 
     * make the search (i.e : Google,Yahoo,etc..)
     * @param engine 
     */
    public void setSearchEngine(SearchEngine engine){
        this.searchEngine=engine;
    }
    
    public ArrayList<String> getUrls(){
        return listeUrls;
    }
    
    /**
     * Method allowing to get an URL from the list if the list 
     * is not empty
     * @return String An URL from the list
     */
    public synchronized String tryGetUrl(){
        String url="";
        if(!listeUrls.isEmpty()){
            url = listeUrls.get(0);
            listeUrls.remove(0);
            urlsAlreadyTreated.add(url);
        }
                 
        return url;
    }
    
    /**
     * Method to know if an url has already been processed
     * for the current search
     * @param url
     * @return True if the url has already  been processed , false otherwise
     */
    public synchronized boolean hasAlreadyTreatedUrl(String url){
        return urlsAlreadyTreated.contains(url);
    }
    
    /**
     * Method to know the depth level for the current search
     * @return The level of the current search
     */
    public int getLevelSearch(){
        return search.getLevel();
    }
    
    /**
     * Method to know the limit number of the results to produce for the current search
     * @return The limit number 
     */
    public int getLimitSearch(){
        return search.getLimit();
    }
    
    /**
     * Method which adds an email found to the list of the current search
     * @param email An email found in the document/page
     */
    public synchronized void addEmail(String email,String site){
        search.addEmail(email,site);
    }
}
