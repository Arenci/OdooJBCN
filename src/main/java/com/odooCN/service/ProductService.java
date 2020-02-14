package com.odooCN.service;

import java.net.MalformedURLException;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
	
	
	
	@GET
	@Path("getProductsFromInvoice")
    @Produces(MediaType.APPLICATION_JSON)
	public Object getProductsFromInvoice(@QueryParam("userId") int userId, @QueryParam("invoiceId") int invoiceId) {
		Object results = productBean.getAllProductsFromInvoice(userId, invoiceId);
		return results;
	}
	
	
	@POST
    @Path("addProductToInvoice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProductToInvoice(@QueryParam("userId") int userId, Product product) throws MalformedURLException {				
        productBean.addProductToInvoice(userId, product);
        return Response.status(200).entity(status).type(MediaType.APPLICATION_JSON).build();

	}
	
	@PUT
	@Path("updateProductWithinInvoice")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProductWithinInvoice(@QueryParam("userId") int userId, Product product) {
		productBean.updateProductInInvoice(userId, product);
		return Response.status(200).entity(status).type(MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@Path("deleteProduct")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteInvoice(@QueryParam("userId") int userId, @QueryParam("invoiceLineId") int invoiceLineId) {
		productBean.deleteProductFromInvoice(userId, invoiceLineId);
		return Response.status(200).entity(status).type(MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("getAllShelvesFromWarehouse")
    @Produces(MediaType.APPLICATION_JSON)
	public Object getProductsFromWarehouse(@QueryParam("userId") int userId, @QueryParam("warehouseId") int warehouseId) {
		Object results = productBean.getAllShelvesFromWarehouse(userId, warehouseId);
		return results;
	}
	
	@GET
	@Path("getWarehouses")
    @Produces(MediaType.APPLICATION_JSON)
	public Object getWarehouses(@QueryParam("userId") int userId) {
		Object results = productBean.getWarehouses(userId);
		return results;
	}
	
	@GET
	@Path("getProductById")
    @Produces(MediaType.APPLICATION_JSON)
	public Object getProductById(@QueryParam("userId") int userId, @QueryParam("productId") int productId, @QueryParam("locationId") int locationId) {
		Object results = productBean.getProductById(userId, productId, locationId);
		return results;
	}
	
}
