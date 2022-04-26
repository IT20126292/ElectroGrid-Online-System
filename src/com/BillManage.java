package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Bill;
//import model.Customer;

@Path("/Bill")
public class BillManage {
	Bill BillObj = new Bill();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBill() {
		return BillObj.readBill();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBill(
			@FormParam("billName") String billName,			
	 @FormParam("billAmount") String billAmount,
	 @FormParam("billDate") String billDate,
	 @FormParam("NoOfTunits") String NoOfTunits,
	 @FormParam("BillAr") String BillAr)
	{
	 String output = BillObj.insertBill(billName, billAmount, billDate, NoOfTunits, BillAr);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(String billData)
	{
	//Convert the input string to a JSON object
	 JsonObject cObject = new JsonParser().parse(billData).getAsJsonObject();
	//Read the values from the JSON object
	 String billID = cObject.get("billID").getAsString();
	 String billName = cObject.get("billName").getAsString();
	 String billAmount = cObject.get("billAmount").getAsString();
	 String billDate = cObject.get("billDate").getAsString();
	 String NoOfTunits = cObject.get("NoOfTunits").getAsString();
	 String BillAr = cObject.get("BillAr").getAsString();
	 String output = BillObj.updateBill(billID, billName, billAmount, billDate, NoOfTunits, BillAr);
	return output;
	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(String billData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String billID = doc.select("billID").text();
	 String output = BillObj.deleteBill(billID);
	return output;
	}
	
}
