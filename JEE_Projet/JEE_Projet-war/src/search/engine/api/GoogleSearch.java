/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine.api;

import crawler.ResultSearch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Class representing the Google Engine
 * @author Stef
 */



public class GoogleSearch implements SearchEngine{
    
    /**
     * The keyword used to make the search
     */
    private final String keySearch;
    
    private final String ENGINE_NAME = "GOOGLE";
    
    public GoogleSearch(String keySearch) throws IOException{
       this.keySearch=keySearch.replace(" ", "%20");
    }
    
    
    
    /**
     * 
     * @return A JSONObject returned by the Google API
     * @throws MalformedURLException
     * @throws IOException 
     */
    @Override
    public JSONObject  getResultApi() throws MalformedURLException, IOException{
                        
                
                URL url = new URL("https://ajax.googleapis.com/ajax/services/feed/find?" +
                  "v=1.0&q="+keySearch+"&userip=INSERT-USER-IP");
        URLConnection connection = url.openConnection();
        //connection.addRequestProperty("Referer", "test.com");

        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        String result = builder.toString();
                        
        return  new JSONObject(result);
    }
    
   
    
       /**
     * 
     * @return The list of URLs (with format : http://www.example.com/path/to/something ) found by the Google API
     * @throws IOException 
     */
    @Override
    public ArrayList<ResultSearch> findUrls() throws IOException{
        ArrayList<ResultSearch> urls= new ArrayList<>();
        JSONObject resultApi = getResultApi();
        JSONObject data = (JSONObject)resultApi.get("responseData");
        JSONArray results = (JSONArray)data.get("entries");
        
         for(int i = 0 ;i<results.length();i++){
            JSONObject resultEngine = (JSONObject)results.get(i);
            URL url = new URL(resultEngine.getString("url"));
            ResultSearch resultSearch = new ResultSearch("http://"+url.getHost(),1);
            urls.add(resultSearch);
        }
        return urls;
    }

    @Override
    public String getNameEngine() {
        return ENGINE_NAME;
    }
    
    
    
 
}
