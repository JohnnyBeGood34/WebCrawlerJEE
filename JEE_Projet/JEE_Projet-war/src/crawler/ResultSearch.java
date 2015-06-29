/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;



/**
 * Class representing a result from the search 
 * @author Stef
 */
public class ResultSearch {
    
    /**
     * The string url 
     */
    private final String url;
    
    /**
     * The current level of depth for this result
     */
    private final int currentDepthLevel;
    
    /**
     * Construct a new result
     * @param url the url found from the search
     * @param level The depth of the result's search
     */
    public ResultSearch(String url,int level){
        this.url=url;
        this.currentDepthLevel=level;
    }
    
    
    public String getUrl(){
        return url;
    }
    
    public int getCurrentLevel(){
        return currentDepthLevel;
    }
}
