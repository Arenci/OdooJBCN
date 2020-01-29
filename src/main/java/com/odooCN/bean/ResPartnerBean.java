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

	
	final String url = "http://192.168.103.99:8069",
            db = "ProyectoEmpresa",
      username = "carlosha98@gmail.com",
      password = "1234";
	//final XmlRpcClient client = new XmlRpcClient();
	XmlRpcClient models = new XmlRpcClient();
	final XmlRpcClientConfigImpl clientConfig = new XmlRpcClientConfigImpl();
	final XmlRpcClientConfigImpl auth= new XmlRpcClientConfigImpl();
	int uid;
	
	
	

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
	public Object getAllSuppliers () {
		Object prueba = null;
		List ids;
		
		try {
			auth.setServerURL(
				    new URL(String.format("%s/xmlrpc/2/common", url)));
			 //prueba = client.execute(clientConfig, "version", emptyList());
			 
			clientConfig.setServerURL(new URL(String.format("%s/xmlrpc/2/object", url)));
			models.setConfig(clientConfig);
			
			
			 uid = (Integer) models.execute(auth, "authenticate", asList(
				        db, username, password, emptyMap()));
			 
				ids = asList((Object[])models.execute("execute_kw", asList(
					    db, uid, password,
					    "res.partner", "search",
					    asList(asList(
					        asList("supplier", "=", true),
					        asList("is_company", "=", true)))
					)));
				
				
				prueba = models.execute(
					    "execute_kw", asList(
					        db, uid, password,
					        "res.partner", "read",
					        asList(ids),
					        new HashMap() {{
					            put("fields", asList("name"));
					        }}
					    ));
				
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (XmlRpcException e) {
			e.printStackTrace();
		} 
		
		return prueba;
		
	}
	
	public void createProduct() {
		try {
			auth.setServerURL(
				    new URL(String.format("%s/xmlrpc/2/common", url)));
			uid = (Integer) models.execute(auth, "authenticate", asList(
			        db, username, password, emptyMap()));
			
			clientConfig.setServerURL(new URL(String.format("%s/xmlrpc/2/object", url)));
			models.setConfig(clientConfig);
			 Integer id = (Integer) models.execute("execute_kw", asList(
						db, uid, password,
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
