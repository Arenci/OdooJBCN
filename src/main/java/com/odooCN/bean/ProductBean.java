package com.odooCN.bean;

import static java.util.Arrays.asList;

import java.util.HashMap;

import javax.ejb.Stateless;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import com.odooCN.entity.AuthConfig;
import com.odooCN.entity.Product;
import com.odooCN.entity.User;
import java.util.List;

@Stateless
public class ProductBean {
	String database = "ProyectoEmpresa";
	
	public Object getAllProductsFromInvoice(int userId, int invoiceId) {
		AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();
        Object products = null;
        List ids;
        try {
			ids = asList((Object[])client.execute("execute_kw", asList(
			        database, userId, user.getPassword(),
			        "account.invoice.line", "search",
			        asList(asList(
			            asList("invoice_id", "=", invoiceId)
			           ))
			    )));
			
			

            products = client.execute(
                    "execute_kw", asList(
                        database, userId, user.getPassword(),
                        "account.invoice.line", "read",
                        asList(ids),
                        new HashMap() {{
                            put("fields", asList("id","name","price_unit","price_total","quantity"));
                        }}
                    ));
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                
		return products;
		
	}
	
	
	public void addProductToInvoice(int userId, final Product product) {
    	AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();
    	 try {                                   
          @SuppressWarnings("unchecked")
		final
          Integer id = (Integer) client.execute("execute_kw", asList(
                   database, userId, user.getPassword(),
                      "account.invoice.line", "create",
                      asList(new HashMap() {
                          {
                              put("name", product.getName());
                              put("invoice_id", product.getInvoice_id());
                              put("product_id", product.getProduct_id());
                              put("price_unit", product.getPrice_unit()); 
                              put("quantity", product.getQuantity());
                              put("account_id",480);
                          }
                      })));
          
          
          
      } catch (XmlRpcException e) {

          e.printStackTrace();
      }
    	 
    	 
}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "serial","rawtypes" })
	public void updateProductInInvoice(int userId, final Product product) {
		AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();
        Object id;
        Product prueba = null;
        
        
        try {
			client.execute("execute_kw", asList(
				    database, userId, user.getPassword(),
				    "account.invoice.line", "write",
				    asList(
				        asList(product.getInvoice_line_id()),				        
				        new HashMap() {{ 
				        put("name", product.getName());	
                        put("price_unit", product.getPrice_unit()); 
                        put("quantity", product.getQuantity());				        				
				        }}
				    )
				));
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteProductFromInvoice(int userId, int invoiceLineId) {
		AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();
        try {
        	client.execute("execute_kw", asList(
                    database, userId, user.getPassword(),
                    "account.invoice.line", "unlink",
                    asList(asList(invoiceLineId))));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
	}
	
	public Object getAllShelvesFromWarehouse(int userId, int  warehouseId) {
		Object shelves = null;
		AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();
        
        try {
        	shelves = asList((Object[])client.execute("execute_kw", asList(
                    database, userId, user.getPassword(),
                    "stock.location", "search_read", 
                    asList(asList(
                    		asList("location_id","=", warehouseId)))                  		
                )));                
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }               
		return shelves;
	}
	
	public Object getWarehouses(int userId) {
		Object warehouses = null;
		AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();
        
        try {
			warehouses = asList((Object[])client.execute("execute_kw", asList(
			        database, userId, user.getPassword(),
			        "stock.warehouse", "search_read", 
			        asList(asList())                  		
			    )));
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
        
		return warehouses;
	}
	
	public Object getProductById(int userId, int productId, int locationId) {
		Object product = null;
		AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();
        try {
        	product = asList((Object[])client.execute("execute_kw", asList(
                    database, userId, user.getPassword(),
                    "stock.quant", "search_read", 
                    asList(asList(
                    		asList("product_id","=", productId),
                    		asList("location_id", "=",locationId)))                  		
                )));                
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }               
		return product;
	}
}
