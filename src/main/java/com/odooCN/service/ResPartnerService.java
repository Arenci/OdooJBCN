package com.odooCN.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public Object getSuppliersUsingXmlrpc() {				
        Object results = resPartnerBean.getAllSuppliers();
        return results;
    }
	
	@GET
	@Path("p")
	@Produces(MediaType.APPLICATION_JSON)
	public void addProduct() {
		resPartnerBean.createProduct();
	}
	
	
}
