/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.readerwriter;


import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import util.Operation;

/**
 * REST Web Service
 *
 * @author vito
 */
@Path("writer")
@RequestScoped
public class WriterApi {

    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WriterApi
     */
    public WriterApi() {
    }   

    /**
     * PUT method for updating or creating an instance of WriterApi
     * @param content representation for the resource
     */
    @POST
    @Path("/add/{name}/{value}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void put(@PathParam("name") String name, @PathParam("value") String value) {
        Operation op = new Operation(name,value);
        Client client = ClientBuilder.newClient(); 
        String out = name + value;
        WebTarget webTarget   = client.target("http://replica:8080/ReplicaManagerHomework2-web/webresources/operazioni/add");
        Response response  = webTarget.request(MediaType.TEXT_PLAIN).post(Entity.entity(out, MediaType.TEXT_PLAIN));
        //return response;
        //return client.target("" ).path(name).path(String.valueOf(value)).request(MediaType.TEXT_PLAIN).post(Entity.entity("", MediaType.TEXT_PLAIN));

    }
    @GET
    @Path("/all/{name}/{value}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAll(@PathParam("name") String name, @PathParam("value") String value) {
       
       
        Client client = ClientBuilder.newClient(); 
      
           return client.target("http://replica:8080/ReplicaManagerHomework2-web/webresources/operazioni/get").path(name).path(value).request(MediaType.TEXT_PLAIN).get(String.class);
	
    }
}
