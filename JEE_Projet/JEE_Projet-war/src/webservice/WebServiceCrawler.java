/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice;

import crawler.CrawlerManager;
import java.io.IOException;
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
     * Web service operation
     * @param inputSearch
     * @param deepLevel
     * @return 
     * @throws java.io.IOException 
     */
    @WebMethod(operationName = "getResultFromSearchCrawler")
    public List<String> getResultFromSearchCrawler(@WebParam(name = "inputSearch") String inputSearch,
            @WebParam(name = "deepLevel") int deepLevel, @WebParam(name = "limit") int limit) throws IOException {
        CrawlerManager result = new CrawlerManager(inputSearch, deepLevel,limit);
        List<String> resultListEmailsFromSearch;
        resultListEmailsFromSearch = result.getResultFromSearchCrawler();
        return resultListEmailsFromSearch;
    }
}
