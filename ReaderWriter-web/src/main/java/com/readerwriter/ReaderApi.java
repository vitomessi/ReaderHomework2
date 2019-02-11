/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.readerwriter;


import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;


/**
 * REST Web Service
 *
 * @author vito
 */
@Path("reader")
@RequestScoped
public class ReaderApi {
/*private static int readQuorum = Integer.parseInt(System.getenv("READ_QUORUM"));
private static String REPLICA_MANAGER_NAME = "replica-manager";
public static int REPLICA_MANAGER_NUM = Integer.parseInt(System.getenv("REPLICA_MANAGER_NUM"));

/** BUILD LIST OF REPLICA MANAGER URLS **/
        
        /*** 
         ** Urls will be like:
         **   - replica-manager-1
         **   - replica-manager-2
         **   - ....
         ***/
        
    //List<String> rm_urls = new ArrayList<String>();    
        
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ReaderApi
     */
    public ReaderApi() {
    }

    /**
     * Retrieves representation of an instance of com.readerwriter.ReaderApi
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/all")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAll() {
        /*
        
        
        for(int i = 0; i < 5; ++i ) {
            rm_urls.add(REPLICA_MANAGER_NAME + "-" + Integer.toString(i) );
        }*/
        Client client = ClientBuilder.newClient(); 
       
        return client.target("http://localhost:8080/ReplicaManagerHomework2-web/webresources/operazioni/getAll").request(MediaType.TEXT_PLAIN).get(String.class);

	
    }

   /**
     * Retrieves representation of an instance of com.readerwriter.ReaderApi
     * @return an instance of java.lang.String
     */
    
    @GET
    @Path("/op/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getOp(@PathParam("name") String name) {
        Client client = ClientBuilder.newClient(); 
       
        return client.target("http://localhost:8080/ReplicaManagerHomework2-web/webresources/operazioni/getOp").path(String.valueOf(name)).request(MediaType.TEXT_PLAIN).get(String.class);
    }
    
    /**
     * Retrieves representation of an instance of com.readerwriter.ReaderApi
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/ord/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getOrdered(@PathParam("name") String name) {
        Client client = ClientBuilder.newClient(); 
       
        return client.target("http://localhost:8080/ReplicaManagerHomework2-web/webresources/operazioni/getOrd").path(String.valueOf(name)).request(MediaType.TEXT_PLAIN).get(String.class);
    }
    
    
}
