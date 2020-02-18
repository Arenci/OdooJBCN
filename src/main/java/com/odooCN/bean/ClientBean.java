package com.odooCN.bean;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import com.odooCN.entity.AuthConfig;
import com.odooCN.entity.User;

@Stateless
public class ClientBean {
	String database = "ProyectoEmpresa";
	
	
	@SuppressWarnings("unchecked")
	public Object getAllCustomers (int userId) {
        Object customers = null;
        List resultsIds;
        AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();
        try {                
                resultsIds = asList((Object[])client.execute("execute_kw", asList(
                        database, userId, user.getPassword(),
                        "res.partner", "search",
                        asList(asList(
                            asList("customer", "=", true)
                           ))
                    )));                
    
                customers = client.execute(
                        "execute_kw", asList(
                            database, userId, user.getPassword(),
                            "res.partner", "read",
                            asList(resultsIds),
                            new HashMap() {{
                                put("fields", asList("id","name","website","street","vat","phone","email","image"));
                            }}
                        ));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        } 
        
        return customers;
        
    }
	@SuppressWarnings("unchecked")
	public Object getClientById(int userId, int customerId) {
		Object customer = null;
		List resultId;
		AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();            
        
        try {
            customer = client.execute(
                    "execute_kw", asList(
                        database, userId, user.getPassword(),
                        "res.partner", "read",
                        asList(customerId),
                        new HashMap() {{
                            put("fields", asList("name","website","street","vat","phone","email", "image"));
                        }}
                    ));
        }catch (XmlRpcException e) {
            e.printStackTrace();
        } 
        
		return customer;
	}
}
