
package crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
     * 
     * @param poolUrls An object of type Pool
     */
    public Searcher(Pool poolUrls){
        this.poolUrls=poolUrls;
    }
    
    /**
     * Method which analyze a HTML page with a given URL
     * @param urlToSearch The url with following plain format : http://www.example.com/path/to/something
     * @throws MalformedURLException
     * @throws IOException 
     */
    private void searchInPage(String urlToSearch) throws MalformedURLException, IOException {
       
       System.out.println("Thread n� "+ this.getId()+"  Analyzing ... "+urlToSearch);
               
        URL url = new URL(urlToSearch);
        String domainName = url.getHost();
       
        Document doc = Jsoup.connect(urlToSearch).get();
        findLinksIntoPage(doc,domainName);
        findEmailsIntoPage(doc,domainName);
    }
    
    /**
     * Method which finds links in a given HTML Document
     * @param dom Represents the DOM of a HTML document
     * @param domainName The domain name of the document we are scanning to save it in the pool
     * @throws MalformedURLException 
     */
    private void findLinksIntoPage(Document dom ,String domainName) throws MalformedURLException{
          Elements links = dom.select("a");
        System.out.println("NB LINKS : "+links.size());
        for(Element link:links){
                
           String urlFound = link.attr("href");                                                               
            System.out.println("URL TO ADD : "+urlFound);
            if(isUrlValid(urlFound,domainName)) { 
               
                String urlToAdd = urlFound.contains("http") ? urlFound : "http://"+domainName+urlFound ;                                                                
                
                addUrl(urlToAdd);
               
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
        

       int level = (url.contains("http")) ? url.split("/").length -2 : url.split("/").length-1;       
       boolean hasDeeperLevel = level > this.poolUrls.getLevelSearch();// number of slash in relative path > number of slash wanted by level search
        System.out.println("Level URL : "+level+"  "+this.poolUrls.getLevelSearch());
        return (url.contains("#")==false) // The link is not an anchor
                && (url.equals("")==false) // The link is not empty
                && (url.equals("/")==false) //The link is not just a slash                 
                &&  (url.contains(domainName)) //The link must not lead to another website/domain               
                && (poolUrls.hasAlreadyTreatedUrl(url) == false)//The link has not been processed yet
                && (hasDeeperLevel==false);//The link has not a deeper level than the level search
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
              poolUrls.addEmail(matcher.group(),site);
          }                   
    }
    
    /**
     * Main method of the thread : take an url from the pool and process it
     * while it can get one
     */
    @Override
     public void run(){
         
        String url = poolUrls.tryGetUrl();
        while(!url.equals("")){
            try {
               searchInPage(url);
            } catch (IOException ex) {                
                   // System.out.println("Probleme de connexion ou  url malform�e : "+url);
            }
            url=poolUrls.tryGetUrl();
        }
    }
    
    
    /**
     * Method which adds an url to the pool
     * @param url 
     */ 
    private  synchronized void addUrl(String url){
        poolUrls.addUrl(url);
    }
    
   
    
    
}
