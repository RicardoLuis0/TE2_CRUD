package com.ricardo.te2crud;

import java.sql.SQLException;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ricardo.te2crud.db.AbstractDB;
import com.ricardo.te2crud.db.DB;
import com.ricardo.te2crud.db.data.DBProduct;

@Path("products")
public class Products {
	
	public static AbstractDB db=new DB();
	
	public static final String key="PASSWORD";

    @Path("search")
    @GET
	@Produces(MediaType.APPLICATION_JSON)
    public Response searchProducts(@QueryParam("q") String query) throws SQLException {
    	return Response.ok(db.searchProducts(query)).build();
    }

    @Path("add")
    @GET
	@Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(@QueryParam("key") String c_key,@QueryParam("name") String name,@QueryParam("img_url") String img_url,@QueryParam("short_description") String short_description,@QueryParam("long_description") String long_description) throws SQLException {
    	if(!c_key.equals(key))return Response.serverError().build();
    	return Response.ok(db.addProduct(new DBProduct(-1,name,img_url,short_description,long_description))).build();
    }

    @Path("get")
    @GET
	@Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@QueryParam("id") int id) throws SQLException {
    	return Response.ok(db.getProduct(id)).build();
    }

    @Path("update")
    @GET
	@Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@QueryParam("key") String c_key,@QueryParam("id") int id,@DefaultValue("null") @QueryParam("name") String name,@DefaultValue("null") @QueryParam("img_url") String img_url,@DefaultValue("null") @QueryParam("short_description") String short_description,@DefaultValue("null") @QueryParam("long_description") String long_description) throws SQLException {
    	if(!c_key.equals(key))return Response.serverError().build();
    	DBProduct prod=db.getProduct(id);
    	if(prod!=null) {
    		boolean mod=false;
    		if(!(name.equals("null")||name.equals(prod.getName()))) {
    			mod=true;
    			prod.setName(name);
    		}
    		if(!(img_url.equals("null")||img_url.equals(prod.getImgUrl()))) {
    			mod=true;
    			prod.setImg_url(img_url);
    		}
    		if(!(short_description.equals("null")||short_description.equals(prod.getShortDescription()))) {
    			mod=true;
    			prod.setShortDescription(short_description);
    		}
    		if(!(long_description.equals("null")||long_description.equals(prod.getLongDescription()))) {
    			mod=true;
    			prod.setLongDescription(long_description);
    		}
    		if(mod) {//if the input data is different than the database object, update it
    			if(!db.updateProduct(prod)) {
    				return Response.ok(null).build();
    			}
    		}
    	}
    	return Response.ok(prod).build();
    }

    @Path("remove")
    @GET
	@Produces(MediaType.APPLICATION_JSON)
    public Response removeProduct(@QueryParam("key") String c_key,@QueryParam("id") int id) throws SQLException {
    	if(!c_key.equals(key))return Response.serverError().build();
    	return Response.ok(db.removeProduct(id)).build();
    }

}
