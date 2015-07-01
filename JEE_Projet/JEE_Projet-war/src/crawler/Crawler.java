/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Class to test the crawler
 * @author Stef
 */
public class Crawler
  {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException
      {
          
          CrawlerManager crawler = new CrawlerManager("montpellier fête musique",1,100);
          List<String> results = crawler.getResultFromSearchCrawler();
          
          for(String aresult:results) System.out.println(aresult);

      }
          

  }
