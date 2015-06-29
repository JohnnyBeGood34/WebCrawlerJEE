/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine.api;

import crawler.ResultSearch;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author Stef
 */
public class YahooSearch implements SearchEngine{

     private final String ENGINE_NAME = "YAHOO";
    
    @Override
    public JSONObject getResultApi() throws MalformedURLException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ResultSearch> findUrls() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNameEngine() {
       return ENGINE_NAME;
    }

   
    
}
