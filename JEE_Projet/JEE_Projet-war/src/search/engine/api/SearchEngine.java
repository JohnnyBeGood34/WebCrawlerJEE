/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author Stef
 */
public interface SearchEngine {
        
         
         public JSONObject  getResultApi()throws MalformedURLException, IOException;        
          public ArrayList<String> findUrls() throws IOException;
    
}
