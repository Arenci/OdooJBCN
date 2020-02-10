package com.odooCN.service;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.odooCN.bean.ClientBean;

@Path("Customer")
public class ClientService {
	
	@EJB
	ClientBean clientBean;

	private final String status = "{\"status\":\"ok\"}";
	
	@GET
    @Path("getCustomers")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getSuppliersUsingXmlrpc(@QueryParam("userId") int userId) {				
        Object results = clientBean.getAllCustomers(userId);
        return results;
    }
	
	@GET
    @Path("getCustomerById")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getCustomerUsingXmlrpc(@QueryParam("userId") int userId, @QueryParam("customerId") int customerId) {				
        Object results = clientBean.getClientById(userId, customerId);
        return results;
    }
}
