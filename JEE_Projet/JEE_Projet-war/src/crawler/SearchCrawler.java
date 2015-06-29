
package crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a search made by the user
 * @author Stef
 */
public class SearchCrawler {
    
    /**
     * The depth level of the search in the tree of the site
     */
    private int level;
    
    /**
     * The limit of th search : how many results we want to find
     * in case of restriction
     */
    private int limit;
    
    /**
     *The word used by the user to make his search
     */
    private String keyword;
    
    /**
     * The results of the research : all mails found 
     * the different pages by website
     */
    private static HashMap<String,ArrayList<String>> mails;
    
    /**
     * 
     * @param keyword The keyword of the search
     * @param level The depth level
     */
    public SearchCrawler(String keyword,int level){
        this.level=level;
        this.keyword=keyword;
        mails=new HashMap<>();
    }
    
    /**
     * 
     * @return the number of emails found for this search
     */
    public synchronized int size(){
        
        int size = 0;
        
        for (Map.Entry<String, ArrayList<String>> entry : mails.entrySet())
          {
            ArrayList<String> mailsBySite = entry.getValue();
             
            size+= mailsBySite.size();
          }
        return size;
    }
    
    /**
     * 
     * @return level of depth for search
     */
    public int getLevel() {
        return level;
    }

    /**
     * 
     * @param level 
     */
    public void setLevel(int level) {
        this.level = level;
    }
    
    /**
     * 
     * @return the keyword used for the search
     */
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * To get all the mails found in a search by website
     * @return The list of all mails by website
     */
    public HashMap<String,ArrayList<String>> getMails() {
        return mails;
    }

    public void setLimit(int limit){
        this.limit= limit;
    }
    
    /**
     * 
     * @return the limit 
     */
    public int getLimit(){
        return this.limit;
    }
    
    /**
     * Method to add an email found to the list
     * @param email An email found in a website page
     * @param site
     */
    public  synchronized void addEmail(String email,String site){
        
       ArrayList<String> results = mails.get(site);
       
       if( results == null) results = new ArrayList<>();
       
       if(results.contains(email) == false && email.contains("noreply") == false){         
            mails.remove(site);
           results.add(email);
           mails.put(site, results);
       }
    }
}
