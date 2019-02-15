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
public static int REPLICA_MANAGER_NUM = Integer.parseInt(System.getenv("REPLICA_MANAGER_NUM"));*/


        
        
        
        
       
        
    //List<String> rm_urls = new ArrayList<String>();    
    private int readQuorum = 1;
    private int counterAck = 0;   
    private static final String REST_URI = "http://replica:8080/ReplicaManagerHomework2-web/webresources/operazioni"; 
    
    //BUILD LIST OF REPLICA MANAGER URLS **/
        
        /*** 
         ** Urls will be like:
         **   - replica-manager-1
         **   - replica-manager-2
         **   - ....
         ***/
     /*
    
        
        for(int i = 0; i < 5; ++i ) {
            rm_urls.add(REPLICA_MANAGER_NAME + "-" + Integer.toString(i) );
    
        }*/
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ReaderApi
     */
    public ReaderApi() {

    }
    /**
     * ricezione ack da parte del replicaManager
     */
    @GET         
    @Produces(MediaType.TEXT_PLAIN)     
    public int readerAck(){              
        /*
        Client client = ClientBuilder.newClient();  
        for(int i = 0; i < 5; i++){
        if ("STATUS_OK_200".equals(client.target("http://" + rm_urls[i] + ":8080/ReplicaManagerHomework2-web/webresources/operazioni").path("sendAck").request(MediaType.TEXT_PLAIN).get(String.class)))
        {
            counterAck++;              
        }  
        }*/
        Client client = ClientBuilder.newClient();           
        if ("STATUS_OK_200".equals(client.target(REST_URI).path("sendAck").request(MediaType.TEXT_PLAIN).get(String.class)))
        {
            counterAck++;              
        }      
        return counterAck;
    }
    

    /**
     * Get of all elements of rm
     * @return 
     */
    @GET
    @Path("/all")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAll() {
       
        int count = readerAck();
        Client client = ClientBuilder.newClient(); 
         if (count >= readQuorum){ 
             /*for (int i = 0; i<READ_QUORUM; i++){
             return client.target("http://" + rm_urls[Math.random() * 5] + ":8080/ReplicaManagerHomework2-web/webresources/operazioni").path("all").request(MediaType.TEXT_PLAIN).get(String.class);
             }*/
           return client.target(REST_URI).path("all").request(MediaType.TEXT_PLAIN).get(String.class);
         }else return "Error!";
	
    }

   /**
     * Get the elements of name {name}
     * @param name
     * @return an instance of java.lang.String
     */
    
    @GET
    @Path("/op/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getOp(@PathParam("name") String name) {
        int count = readerAck();
        Client client = ClientBuilder.newClient(); 
        if (count >= readQuorum){ 
             /*for (int i = 0; i<READ_QUORUM; i++){
             return client.target("http://" + rm_urls[Math.random() * 5] + ":8080/ReplicaManagerHomework2-web/webresources/operazioni").path("op").path(name).request(MediaType.TEXT_PLAIN).get(String.class);
             }*/
        return client.target(REST_URI).path("op").path(name).request(MediaType.TEXT_PLAIN).get(String.class);
         }else return "Error!";
    }
    
    /**
     * Get the elements of name {name} in order by insert
     * @param name
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/ord/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getOrdered(@PathParam("name") String name) {
        int count = readerAck();
        Client client = ClientBuilder.newClient(); 
        if (count >= readQuorum){
            /*for (int i = 0; i<READ_QUORUM; i++){
             return client.target("http://" + rm_urls[Math.random() * 5] + ":8080/ReplicaManagerHomework2-web/webresources/operazioni").path("ord").request(MediaType.TEXT_PLAIN).get(String.class);
             }*/
        return client.target(REST_URI).path("ord").path(String.valueOf(name)).request(MediaType.TEXT_PLAIN).get(String.class);
         }else return "Error!";
    }
    
    
}
