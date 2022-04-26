package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Customer;

@Path("/Customer")
public class CustomerManage {
	Customer CustomerObj = new Customer();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomer() {
		return CustomerObj.readCustomer();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(
			@FormParam("cus_Name") String cus_Name,			
	 @FormParam("cus_Nic") String cus_Nic,
	 @FormParam("cus_addr") String cus_addr,
	 @FormParam("cus_pnumber") String cus_pnumber,
	 @FormParam("cus_email") String cus_email,
	@FormParam("cus_pwd") String cus_pwd)
	{
	 String output = CustomerObj.insertCustomer(cus_Name, cus_Nic, cus_addr, cus_pnumber, cus_email,cus_pwd);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String customerData)
	{
	//Convert the input string to a JSON object
	 JsonObject cObject = new JsonParser().parse(customerData).getAsJsonObject();
	//Read the values from the JSON object
	 String cID = cObject.get("cID").getAsString();
	 String cus_Name = cObject.get("cus_Name").getAsString();
	 String cus_Nic = cObject.get("cus_Nic").getAsString();
	 String cus_addr = cObject.get("cus_addr").getAsString();
	 String cus_pnumber = cObject.get("cus_pnumber").getAsString();
	 String cus_email = cObject.get("cus_email").getAsString();
	 String cus_pwd = cObject.get("cus_pwd").getAsString();
	 String output = CustomerObj.updateCustomer(cID, cus_Name, cus_Nic, cus_addr, cus_pnumber, cus_email,cus_pwd);
	return output;
	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String customerData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String cID = doc.select("cID").text();
	 String output = CustomerObj.deleteCustomer(cID);
	return output;
	}
	
}
