
package crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;

/**
 *Class with reponsability to search,through HTML pages,all interesting links and emails
 * This class inherits Thread class so several Search objects will be launched in order to 
 * make the reasearch processing
 * 
 * @author Stef
 */
public class Searcher extends Thread{
    
    /**
     * An object of type Pool
     */
    private final Pool poolUrls;
    
    
    /**
     * Object representing a search made by the user
     */
    private final SearchCrawler search;
    
    
    /**
     * 
     * @param poolUrls A pool of results
     * @param search A new search
     */
    public Searcher(Pool poolUrls,SearchCrawler search){
        this.poolUrls=poolUrls;
        this.search=search;
    }
    
    /**
     * Method which analyze a HTML page with a given URL
     * @param result A result from the pool with an url having the following plain format : http://www.example.com/path/to/something
     * @throws MalformedURLException
     * @throws IOException 
     */
    private void searchInPage(ResultSearch result) throws MalformedURLException, IOException {
       
       System.out.println("Thread n° "+ this.getId()+"  Analyzing ... "+result.getUrl());
               
        URL url = new URL(result.getUrl());
         String domainName = url.getHost();
       
         Document doc = Jsoup.connect(result.getUrl())
                 .userAgent("Mozilla").timeout(3000)
                 .get();
                     
        findLinksIntoPage(doc,domainName,result.getCurrentLevel());
        findEmailsIntoPage(doc,domainName);
    }
    
    /**
     * Method which finds links in a given HTML Document
     * @param dom Represents the DOM of a HTML document
     * @param domainName The domain name of the document we are scanning to save it in the pool
     * @throws MalformedURLException 
     */
    private void findLinksIntoPage(Document dom ,String domainName,int currentLevelSearch) throws MalformedURLException{
          Elements links = dom.select("a");
        System.out.println("NB LINKS : "+links.size());
        
        for(Element link:links){
               
           String urlFound = link.attr("href");                                                                         
            if(currentLevelSearch <= search.getLevel()) { 
                
                  if (isUrlValid(urlFound,domainName)) {
                      String url = urlFound.contains("http") ? urlFound : "http://"+domainName+urlFound ;
                      int newLevel = currentLevelSearch++;
                      ResultSearch newResult = new ResultSearch(url,newLevel);
                      addUrl(newResult);
                  }
               
            }
        }
    }
    
    /**
     * Method which determinate if the link (provided by the href tag )
     * is valid or not 
     * @param url An url link provided by a href tag in HTML page
     * @return boolean True if the given URL is valid, false otherwise
     * @throws MalformedURLException 
     */
    private boolean isUrlValid(String url,String domainName) throws MalformedURLException{
                               
        return (url.contains("#")==false) // The link is not an anchor
                && (url.equals("")==false) // The link is not empty
                && (url.equals("/")==false) //The link is not just a slash                 
                &&  (url.contains(domainName)) //The link must not lead to another website/domain               
                && (poolUrls.hasAlreadyTreatedUrl(url) == false);//The link has not been processed yet
                
              
    }
    
    /**
     * Method which finds emails into a HTML document 
     * and add it to the list of the current search
     * @param dom 
     */
    private void findEmailsIntoPage(Document dom,String site){
       
          Element body = dom.body();
          String content=body.text();
          Matcher matcher = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(content);
          while(matcher.find()){             
              search.addEmail(matcher.group(),site);
          }                   
    }
    
    /**
     * Main method of the thread : take a result from the pool and process it
     * while it can get one
     */
    @Override
     public void run(){
         
        ResultSearch result = poolUrls.tryGetUrl();
        int emails_size = search.size();
        
        while(result != null && emails_size < search.getLimit()){
            System.out.println("EMAILS FOUND : "+emails_size+"  SEARCH LIMIT : "+search.getLimit());
            try {
               searchInPage(result);
            } catch (IOException ex) {                
                    System.out.println("Probleme de connexion ou  url malformï¿½e : "+result.getUrl());
            }
            result=poolUrls.tryGetUrl();
            emails_size = search.size();
        }
    }
    
    
    /**
     * Method which adds a result to the pool
     * @param result A new result found to add to pool
     */ 
    private  synchronized void addUrl(ResultSearch result){
        poolUrls.addUrl(result);
    }
    
   
    
    
}
