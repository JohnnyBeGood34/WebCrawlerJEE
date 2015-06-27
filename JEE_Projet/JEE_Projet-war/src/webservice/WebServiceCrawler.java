/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice;

import crawler.CrawlerManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Kevin
 */
@WebService(serviceName = "WebServiceCrawler")
public class WebServiceCrawler {

    /**
     * This is a sample web service operation
     * @param txt
     * @return 
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    /**
     * Web service operation
     * @param inputSearch
     * @param deepLevel
     * @return
     * @throws java.io.IOException
     */
    @WebMethod(operationName = "getResultFromSearchCrawler")
    public List<String> getResultFromSearchCrawler(String inputSearch, int deepLevel) throws IOException{
        CrawlerManager result = new CrawlerManager(inputSearch, deepLevel);
        List<String> resultListEmailsFromSearch;
        resultListEmailsFromSearch = result.getResultFromSearchCrawler();
        return resultListEmailsFromSearch;
    }
}
