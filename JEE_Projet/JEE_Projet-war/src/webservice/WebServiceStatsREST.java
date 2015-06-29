/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author JOHN
 */
@Path("/tuveuxquoi")
public class WebServiceStatsREST
  {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WebServiceStatsREST
     */
    public WebServiceStatsREST()
      {
      }

    /**
     * Retrieves representation of an instance of webservice.WebServiceStatsREST
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getApplicationStats()
      {
        return "{\n"
                + "    \"labels\": [\n"
                + "        \"January\",\n"
                + "        \"February\",\n"
                + "        \"March\",\n"
                + "        \"April\",\n"
                + "        \"May\",\n"
                + "        \"June\",\n"
                + "        \"July\"\n"
                + "    ],\n"
                + "    \"datasets\": [\n"
                + "        {\n"
                + "            \"label\": \"My First dataset\",\n"
                + "            \"fillColor\": \"rgba(220,220,220,0.2)\",\n"
                + "            \"strokeColor\": \"rgba(220,220,220,1)\",\n"
                + "            \"pointColor\": \"rgba(220,220,220,1)\",\n"
                + "            \"pointStrokeColor\": \"#fff\",\n"
                + "            \"pointHighlightFill\": \"#fff\",\n"
                + "            \"pointHighlightStroke\": \"rgba(220,220,220,1)\",\n"
                + "            \"data\": [\n"
                + "                65,\n"
                + "                59,\n"
                + "                80,\n"
                + "                81,\n"
                + "                56,\n"
                + "                55,\n"
                + "                40\n"
                + "            ]\n"
                + "        },\n"
                + "        {\n"
                + "            \"label\": \"My Second dataset\",\n"
                + "            \"fillColor\": \"rgba(151,187,205,0.2)\",\n"
                + "            \"strokeColor\": \"rgba(151,187,205,1)\",\n"
                + "            \"pointColor\": \"rgba(151,187,205,1)\",\n"
                + "            \"pointStrokeColor\": \"#fff\",\n"
                + "            \"pointHighlightFill\": \"#fff\",\n"
                + "            \"pointHighlightStroke\": \"rgba(151,187,205,1)\",\n"
                + "            \"data\": [\n"
                + "                28,\n"
                + "                48,\n"
                + "                40,\n"
                + "                19,\n"
                + "                86,\n"
                + "                27,\n"
                + "                90\n"
                + "            ]\n"
                + "        }\n"
                + "    ]\n"
                + "}";
      }
  }
