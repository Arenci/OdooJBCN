package com.odooCN.bean;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.json.JSONObject;

import com.odooCN.entity.ResPartner;

@Stateless
public class ResPartnerBean {

	

	String database = "ProyectoEmpresa";
	XmlRpcClient models = new XmlRpcClient();
	final XmlRpcClientConfigImpl clientConfig = new XmlRpcClientConfigImpl();
	final XmlRpcClientConfigImpl auth= new XmlRpcClientConfigImpl();
	int uid;
	//String password1;
	
	String url = "http://192.168.103.55:8069",
            db = "ProyectoEmpresa",
      username = "",
      password = "";	
	

//	@PersistenceContext(unitName="OdooConnection")
//    EntityManager em;+++
//	
//	@SuppressWarnings("unchecked")
//	public List<ResPartner> getSuppliers(){
//		Query q = em.createNamedQuery("ResPartner.findAllSuppliers");
//        List<ResPartner> results = q.getResultList();
//        return results;
//	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public Object getAllSuppliers (int userId) {
		Object suppliers = null;
		List resultsIds;
		try {				 
				resultsIds = asList((Object[])models.execute("execute_kw", asList(
					    database, userId, password,
					    "res.partner", "search",
					    asList(asList(
					        asList("supplier", "=", true),
					        asList("is_company", "=", true)))
					)));				
	
				suppliers = models.execute(
					    "execute_kw", asList(
					        database, userId, password,
					        "res.partner", "read",
					        asList(resultsIds),
					        new HashMap() {{
					            put("fields", asList("name"));
					        }}
					    ));
				
		} catch (XmlRpcException e) {
			e.printStackTrace();
		} 
		
		return suppliers;
		
	}
	
	public void createProduct(int userId) {
		try {									
			 @SuppressWarnings("unchecked")
			Integer id = (Integer) models.execute("execute_kw", asList(
					 database, userId, password,
						"product.template", "create",
						asList(new HashMap() {
							{
								put("name", "Chair");
								put("description", "It's a chair");
								put("type", "service");
								put("list_price", 14.9); 
							}
						})));
		} catch (XmlRpcException e) {

			e.printStackTrace();
		}
		
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public Object getInvoices(int userId) {
		List resultsIds;
		Object invoices = null;

			try {
				resultsIds = asList((Object[])models.execute("execute_kw", asList(
					    database, userId, password,
					    "account.invoice", "search",
					    asList(asList())
					)));
				
				
				invoices = models.execute(
					    "execute_kw", asList(
					        database, userId, password,
					        "account.invoice", "read",
					        asList(resultsIds),
					        new HashMap() {{
					            put("fields", asList("vendor_display_name","type","amount_untaxed","amount_tax","amount_total"));
					        }}
					    ));
				//account_invoice_line
			} catch (XmlRpcException e) {

				e.printStackTrace();
			}				
		
		return invoices;
		
	}
	
	
	public int authenticate(String user, String passwd) {
		
	      username = user;
	      password = passwd;				 	
		try {
			auth.setServerURL(
				    new URL(String.format("%s/xmlrpc/2/common", url)));
			 
			clientConfig.setServerURL(new URL(String.format("%s/xmlrpc/2/object", url)));
			models.setConfig(clientConfig);
			
			try {
				uid = (Integer) models.execute(auth, "authenticate", asList(
				        db, username, password, emptyMap()));		
			} catch(ClassCastException e) {
				uid = -1;
			}
			
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}

		catch (XmlRpcException e) {

			e.printStackTrace();
		}
		return uid;
	}
}
