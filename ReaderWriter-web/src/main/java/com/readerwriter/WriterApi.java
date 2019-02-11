/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.readerwriter;

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
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    @Path("/add/{id}/{name}/{value}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response put(@PathParam("id") int id, @PathParam("name") String name, @PathParam("value") float value) {
        Client client = ClientBuilder.newClient();
        return client.target("http://localhost:8080/ReplicaManagerHomework2-web/webresources/operazioni/add/" /*+ String.valueOf(id) + "/" + name + "/" + String.valueOf(value)*/).path(String.valueOf(id)).path(name).path(String.valueOf(value)).request(MediaType.TEXT_PLAIN).post(Entity.entity("", MediaType.TEXT_PLAIN));

    }
}
