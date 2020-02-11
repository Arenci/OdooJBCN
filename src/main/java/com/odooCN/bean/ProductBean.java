package com.odooCN.bean;

import static java.util.Arrays.asList;

import java.util.HashMap;

import javax.ejb.Stateless;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import com.odooCN.entity.AuthConfig;
import com.odooCN.entity.Product;
import com.odooCN.entity.User;

@Stateless
public class ProductBean {
	String database = "ProyectoEmpresa";
	
	public void addProductToInvoice(int userId, final Product product) {
    	AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();
    	 try {                                   
          @SuppressWarnings("unchecked")
          Integer id = (Integer) client.execute("execute_kw", asList(
                   database, userId, user.getPassword(),
                      "account.invoice.line", "create",
                      asList(new HashMap() {
                          {
                              put("name", product.getName());
                              put("invoice_id", product.getInvoice_id());
                              put("product_id", product.getProduct_id());
                              put("price_unit", product.getPrice_unit()); 
                              put("account_id",480);
                          }
                      })));
      } catch (XmlRpcException e) {

          e.printStackTrace();
      }
}
}
