package com.ricardo.te2crud;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("products")
public class Products {

    @Path("search")
    @GET
	@Produces(MediaType.APPLICATION_JSON)
    public Response searchProducts(@FormParam("q") String query) {//TODO
    	return Response.serverError().build();
    }

    @Path("add")
    @GET
	@Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(@FormParam("name") String name,@FormParam("img_url") String img_url,@FormParam("description") String description) {//TODO
    	return Response.serverError().build();
    }

    @Path("get")
    @GET
	@Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@FormParam("id") int id) {//TODO
    	return Response.serverError().build();
    }

    @Path("update")
    @GET
	@Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@FormParam("id") int id,@DefaultValue("null") @FormParam("name") String name,@DefaultValue("null") @FormParam("img_url") String img_url,@DefaultValue("null") @FormParam("description") String description) {//TODO
    	return Response.serverError().build();
    }

    @Path("remove")
    @GET
	@Produces(MediaType.APPLICATION_JSON)
    public Response removeProduct(@FormParam("id") int id) {//TODO
    	return Response.serverError().build();
    }

}
