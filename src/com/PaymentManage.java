package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Payment;;

@Path("/payment")
public class PaymentManage {
	Payment PaymentObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return PaymentObj.readPayment();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(
			@FormParam("p_status") String p_status,			
	 @FormParam("p_date") String p_date,
	 @FormParam("p_crdnumber") String p_crdnumber,
	 @FormParam("p_cvv") String p_cvv,
	 @FormParam("p_amount") String p_amount
			)
	{
	 String output = PaymentObj.insertPayment(p_status, p_date,p_crdnumber,p_cvv,p_amount);
	return output;
	}
	
//	@PUT
//	@Path("/")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String updateInquiry(String inquiryData)
//	{
//	//Convert the input string to a JSON object
//	 JsonObject cObject = new JsonParser().parse(inquiryData).getAsJsonObject();
//	//Read the values from the JSON object
//	 String p_ID = cObject.get("p_ID").getAsString();
//	 String p_status = cObject.get("p_status").getAsString();
//	 String p_date = cObject.get("p_date").getAsString();
//	 String p_crdnumber = cObject.get("p_crdnumber").getAsString();
//	 String p_amount = cObject.get("p_amount").getAsString();
//	 String output = PaymentObj.updatePayment(p_ID, p_status, p_date, p_crdnumber,p_amount);
//	return output;
//	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String p_ID = doc.select("p_ID").text();
	 String output = PaymentObj.deletePayment(p_ID);
	return output;
	}
	
}
