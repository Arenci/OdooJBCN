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

import com.odooCN.entity.AuthConfig;
import com.odooCN.entity.Invoice;
import com.odooCN.entity.User;


@Stateless
public class InvoiceBean {
	String database = "ProyectoEmpresa";

    @SuppressWarnings({ "unchecked", "rawtypes", "serial" })
    public Object getAllSuppliers (int userId) {
        Object suppliers = null;
        List resultsIds;
        AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();
        try {                
                resultsIds = asList((Object[])client.execute("execute_kw", asList(
                        database, userId, user.getPassword(),
                        "res.partner", "search",
                        asList(asList(
                            asList("supplier", "=", true),
                            asList("is_company", "=", true)))
                    )));                
    
                suppliers = client.execute(
                        "execute_kw", asList(
                            database, userId, user.getPassword(),
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
    
//  public void createProduct(int userId) {
//      try {                                   
//           @SuppressWarnings("unchecked")
//          Integer id = (Integer) models.execute("execute_kw", asList(
//                   database, userId, password,
//                      "product.template", "create",
//                      asList(new HashMap() {
//                          {
//                              put("name", "Chair");
//                              put("description", "It's a chair");
//                              put("type", "service");
//                              put("list_price", 14.9); 
//                          }
//                      })));
//      } catch (XmlRpcException e) {
//
//          e.printStackTrace();
//      }
//      
//  }
    @SuppressWarnings("unchecked")
    public Object getInvoices(int userId) throws MalformedURLException {
        //account_invoice_line
        List resultsIds;
        Object invoices = null;
        String passwordAux = "1234";
        AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();

            try {
                invoices = asList((Object[])client.execute("execute_kw", asList(
                        database, userId, passwordAux,
                        "account.invoice", "search_read",
                        asList(asList())
                    )));                             
//                //account_invoice_line
            } catch (XmlRpcException e) {
                e.printStackTrace();
            }               
        
        return invoices;
        
    }
    
    @SuppressWarnings("unchecked")
    public void deleteInvoice(int userId, int invoiceId) {      
    	AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();
        try {
        	client.execute("execute_kw", asList(
                    database, userId, user.getPassword(),
                    "account.invoice", "unlink",
                    asList(asList(invoiceId))));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
	public void updateInvoice(int userId, final Invoice invoice) {
    	AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();
        try {
			client.execute("execute_kw", asList(
				    database, userId, user.getPassword(),
				    "account.invoice", "write",
				    asList(
				        asList(invoice.getInvoiceId()),
				        
				        new HashMap() {{  put("vendor_display_name", invoice.getVendorDisplayName());
				        				  put("date_due", invoice.getDate_due());
				        				  put("date_invoice", invoice.getDate_invoice());
				        				  put("amount_untaxed",invoice.getAmount_untaxed());
				        				  put("amount_tax",invoice.getAmount_tax());
				        				  put("amount_total",invoice.getAmount_total());
				        				
				        }}
				    )
				));
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void createInvoice(int userId) {
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
                              put("name", "[E-COM11] Cabinet with Doors");
                              put("invoice_id", 3);
                              put("product_id", 15);
                              put("price_unit", 14); 
                              put("account_id",480);
                          }
                      })));
      } catch (XmlRpcException e) {

          e.printStackTrace();
      }
    }
    

}
