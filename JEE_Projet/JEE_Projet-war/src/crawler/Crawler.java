/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import search.engine.api.GoogleSearch;

/**
 *
 * @author Stef
 */
public class Crawler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
 

     
     final int levelSearch = 5;//Niveau de recherche dans l'arborescence des sites
     final int nbThreads =2;//Nombre de threads � utiliser      
     final String keyword = "randonnée";
              
      Search search = new Search(keyword,levelSearch);//Nouvelle recherche
      GoogleSearch googleEngine = new GoogleSearch(keyword);//Le moteur de recherche � utiliser
      System.out.println("Starting pool....");
      Pool pool = new Pool(search,googleEngine);//Construction du pool d'URLs � partir des r�sultats du moteur de recherche
                        
     ArrayList<Searcher> searchers = new ArrayList<>();
     
     for(int i=1;i<=nbThreads;i++){
          Searcher searcher = new Searcher(pool);
          searcher.start();
          searchers.add(searcher);
         
     }
     
     for(Searcher searcher : searchers){
          try {
              searcher.join();
          } catch (InterruptedException ex) {
              Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
          }
     }
        HashMap<String,ArrayList<String>> results;
        results = search.getMails();
        
         System.out.println("");
          System.out.println("");
         for(Map.Entry<String,ArrayList<String>> entry : results.entrySet()){
          
          ArrayList<String> mails = entry.getValue();
          System.out.println("Nombre de r�sultats pour le  site  "+entry.getKey()+"  : "+mails.size());
          
          System.out.println("************Emails trouv�s***************");
          for(String mail : mails){
              System.out.println(mail);
          }
          System.out.println("");
          System.out.println("");
      }
        
        
        
        
    }

}
