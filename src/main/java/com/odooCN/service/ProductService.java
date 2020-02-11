package com.odooCN.service;

import java.net.MalformedURLException;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.odooCN.bean.ProductBean;
import com.odooCN.entity.Product;

@Path("product")
public class ProductService {

	
	@EJB
	ProductBean productBean;
	
	private final String status = "{\"status\":\"ok\"}";
	
	@POST
    @Path("addProduct")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInvoices(@QueryParam("userId") int userId, Product product) throws MalformedURLException {				
        productBean.addProductToInvoice(userId, product);
        return Response.status(200).entity(status).type(MediaType.APPLICATION_JSON).build();

	}
}
