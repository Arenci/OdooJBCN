package com.odooCN.service;

import java.net.MalformedURLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.odooCN.bean.ResPartnerBean;
import com.odooCN.entity.ResPartner;

@Path("supplier")
public class ResPartnerService {
	
	@EJB
	ResPartnerBean resPartnerBean;	
	
	private final String status = "{\"status\":\"ok\"}";
	
//	@GET
//    @Path("getAllSuppliers")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<ResPartner> getSuppliers() {				
//        List<ResPartner> results = resPartnerBean.getSuppliers();
//        return results;
//    }
	
	@GET
    @Path("getSuppliers")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getSuppliersUsingXmlrpc(@QueryParam("id") int id) {				
        Object results = resPartnerBean.getAllSuppliers(id);
        return results;
    }
	
//	@GET
//	@Path("WriteProduct")
//	@Produces(MediaType.APPLICATION_JSON)
//	public void addProduct(@QueryParam("id") int id) {
//		resPartnerBean.createProduct(id);
//	}
	
	
	@GET
	@Path("authenticate")
	@Produces(MediaType.APPLICATION_JSON)
	public Object authenticate(@QueryParam("username") String username,@QueryParam("password") String password)  {
		Object results = resPartnerBean.authenticate(username,password);
		return results;
	}
	
	
	@GET
    @Path("getInvoices")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getInvoices(@QueryParam("userId") int userId) throws MalformedURLException {				
        Object results = resPartnerBean.getInvoices(userId);
        return results;
    }
	
	
	@DELETE
	@Path("deleteInvoice")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteInvoice(@QueryParam("userId") int userId, @QueryParam("invoiceId") int invoiceId) {
		resPartnerBean.deleteInvoice(userId, invoiceId);
		return Response.status(200).entity(status).type(MediaType.APPLICATION_JSON).build();
	}
	
}
