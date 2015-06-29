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
 *
 * @author Stef
 */
public class Crawler
  {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException
      {


//        for (Map.Entry<String, ArrayList<String>> entry : results.entrySet())
//          {
//
//            ArrayList<String> mails = entry.getValue();
//            System.out.println("Nombre de rï¿½sultats pour le  site  " + entry.getKey() + "  : " + mails.size());
//
//            System.out.println("************Emails trouvï¿½s***************");
//            for (String mail : mails)
//              {
//                System.out.println(mail);
//              }
//            System.out.println("");
//            System.out.println("");
//          }
//
          
          CrawlerManager crawler = new CrawlerManager("randonnée",1,1);
          List<String> results = crawler.getResultFromSearchCrawler();
          
          for(String aresult:results) System.out.println(aresult);
      }
          

  }
