package com.odooCN.entity;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class AuthConfig {
    static User user;
    String url = "http://192.168.1.44:8069",
            db = "ProyectoEmpresa";

    private XmlRpcClientConfigImpl authConfiguration() {
    	XmlRpcClientConfigImpl auth = new XmlRpcClientConfigImpl();   	
		try {
			auth.setServerURL(
				  new URL(String.format("%s/xmlrpc/2/common", url)));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
    	return auth;
    }
    
    public XmlRpcClient setModelConfiguration() {
    	XmlRpcClient models = new XmlRpcClient();
    	XmlRpcClientConfigImpl clientConfig = new XmlRpcClientConfigImpl();
    	try {
			clientConfig.setServerURL(new URL(String.format("%s/xmlrpc/2/object", url)));
			clientConfig.setEnabledForExtensions(true);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    	models.setConfig(clientConfig);
		return models; 	
    }
    
    public int authenticate(String username, String password) {
    	int uid = 0;
    	user = new User(username, password);
    	setUser(user);
    	XmlRpcClientConfigImpl authConfig = authConfiguration();
    	XmlRpcClient client = setModelConfiguration();
    	try {
            uid = (Integer) client.execute(authConfig, "authenticate", asList(
                    db, username, password, emptyMap()));       
        } catch(ClassCastException e) {
            uid = -1;
        } catch (XmlRpcException e) {
			e.printStackTrace();
		}
    	return uid;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
    
    
}
