
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
    
    /**
     * 
     * @return the string url
     */
    public String getUrl(){
        return url;
    }
    
    /**
     * 
     * @return the level's search of this result
     */
    public int getCurrentLevel(){
        return currentDepthLevel;
    }
}
