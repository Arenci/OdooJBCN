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
	private final String status = "{\"status\":\"ok\"}";
	
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
    

    @SuppressWarnings("unchecked")
    public Object getInvoices(int userId) throws MalformedURLException {
        Object invoices = null;
        AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();

            try {
                invoices = asList((Object[])client.execute("execute_kw", asList(
                        database, userId, user.getPassword(),
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
    public String deleteInvoice(int userId, int invoiceId) {      
    	AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();
        try {
        	client.execute("execute_kw", asList(
                    database, userId, user.getPassword(),
                    "account.invoice", "unlink",
                    asList(asList(invoiceId))));
        	return status;
        } catch (XmlRpcException e) {
            e.printStackTrace();
            return "Mal";        }
        
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
				        				  put("partner_id", invoice.getPartner_id());
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
    
    public void createInvoice(int userId, final Invoice invoice) {
    	AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();
    	 try {                                   
          @SuppressWarnings("unchecked")
          Integer id = (Integer) client.execute("execute_kw", asList(
                   database, userId, user.getPassword(),
                      "account.invoice", "create",
                      asList(new HashMap() {
                          {
                              put("partner_id", invoice.getPartner_id());
                              put("vendor_display_name", invoice.getVendorDisplayName());
                              put("date_due", invoice.getDate_due());
                              put("date_invoice", invoice.getDate_invoice());
                              put("amount_tax", invoice.getAmount_tax());
                              put("amount_untaxed", invoice.getAmount_untaxed());
                              put("amount_total", invoice.getAmount_total());
                          }
                      })));
      } catch (XmlRpcException e) {

          e.printStackTrace();
      }
    }
    
    public Object getInvoiceById(int userId, int invoiceId) {
    	AuthConfig authConfig = new AuthConfig();       
        XmlRpcClient client = authConfig.setModelConfiguration();
        User user = authConfig.getUser();
        Object invoice = null;
        try {
        	invoice = client.execute("execute_kw", asList(
			        database, userId, user.getPassword(),
			        "account.invoice", "search_read",
			        asList(asList(
			        		asList("id","=", invoiceId)))));
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return invoice;
    }
    

}
