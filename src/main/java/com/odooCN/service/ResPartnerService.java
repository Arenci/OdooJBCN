package com.odooCN.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.odooCN.bean.ResPartnerBean;
import com.odooCN.entity.ResPartner;

@Path("Supplier")
public class ResPartnerService {
	
	@EJB
	ResPartnerBean resPartnerBean;	
	
	
//	@GET
//    @Path("getAllSuppliers")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<ResPartner> getSuppliers() {				
//        List<ResPartner> results = resPartnerBean.getSuppliers();
//        return results;
//    }
	
	@GET
    @Path("a")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getSuppliersUsingXmlrpc(@QueryParam("id") int id) {				
        Object results = resPartnerBean.getAllSuppliers(id);
        return results;
    }
	
	@GET
	@Path("p")
	@Produces(MediaType.APPLICATION_JSON)
	public void addProduct(@QueryParam("id") int id) {
		resPartnerBean.createProduct(id);
	}
	
	
	@GET
	@Path("authenticate")
	@Produces(MediaType.APPLICATION_JSON)
	public Object authenticate(@QueryParam("username") String username,@QueryParam("password") String password)  {
		Object results = resPartnerBean.authenticate(username,password);
		return results;
	}
	
	
	
}
