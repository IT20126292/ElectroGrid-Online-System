package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	// Connect to the DB
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/electri?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");

			// For testing
			System.out.print("Succesfully connected to the DB");
			
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return con;

	}

	         //Insert payment
			public String insertPayment(String billName, String billAmount, String billDate, String NoOfTunits, String BillAr)
			{ 
				Connection con = connect();
				String output = "";
				try
				 { 
					  
					 if (con == null) 
					 { 
					    return "Error while connecting to the database"; 
					 } 
					 
					 // create a prepared statement
					 String query = " insert into bill (`billID`,`billName`,`billAmount`,`billDate`,`NoOfTunits`,`BillAr`)"+ " values (?, ?, ?, ?, ?, ?)"; 
					 
					 PreparedStatement Pstatement = con.prepareStatement(query); 
					 
					 // binding values
					 Pstatement.setInt(1, 0); 
					 Pstatement.setString(2, billName); 
					 Pstatement.setString(3, billAmount); 
					 Pstatement.setString(4, billDate); 
					 Pstatement.setString(5, NoOfTunits);
					 Pstatement.setString(6, BillAr);
					 
					 
					//execute the statement
					 
					 Pstatement.execute(); 
					 con.close();
					// System.out.println(query);
					 String newPayment = readPayment(); 
					 output = "{\"status\":\"success\", \"data\": \"" + 
					 newPayment + "\"}"; 
					 
					// output = "Payment Inserted successfully"; 
				 } 
				
				catch (Exception e) 
				 { 
					 output = "{\"status\":\"error\", \"data\": \"Error while inserting the Payment.\"}"; 
					// output = "Error while inserting"; 
					 
					System.err.println(e.getMessage()); 
				 } 
				//binding values
				return output; 
			}

			//Read the payment
			public String readPayment()
			{ 
				 String output = ""; 
				 
				 try
				 { 
				
			     Connection con = connect(); 
				 if (con == null) 
				 { 
					 return "Error while connecting to the database for reading."; 
				 } 
				 
				 
				 // Prepare the html table to be displayed
				 output = "<table border='1' class='table table-dark table-hover'>"
				 		 + "<tr><th>Bill ID</th>" 
						 +"<th>billName</th>"
						 + "<th>billAmount</th>"
						 + "<th>billDate</th>" 
						 +"<th>NoOfTunits</th>"
						 + "<th>BillAr</th>"
						 + "<th>Update</th>"
						 + "<th>Delete</th></tr>"; 
				 
				 String query = "select * from bill"; 
				 
				 Statement stmt = (Statement) con.createStatement(); 
				 ResultSet res = ((java.sql.Statement) stmt).executeQuery(query); 
				 
				 // iterate through the rows in the result set
				 while (res.next()) 
				 { 
					 String billID = Integer.toString(res.getInt("billID")); 
					 String billName = res.getString("billName"); 
					 String billAmount = res.getString("billAmount"); 
					 String billDate = res.getString("billDate"); 
					 String NoOfTunits = res.getString("NoOfTunits"); 
					 String BillAr = res.getString("BillAr"); 
					 
					 // Add a row into the html table
					 output += "<tr><td>" + billID + "</td>"; 
					 output += "<td>" + billName + "</td>"; 
					 output += "<td>" + billAmount + "</td>";
					 output += "<td>" + billDate + "</td>";
					 output += "<td>" + NoOfTunits + "</td>"; 
					 output += "<td>" + BillAr + "</td>"; 
					 
					 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update' "
							 + "class='btnUpdate btn btn-secondary' data-paymentid='" + billID + "'></td>"
							 + "<td><input name='btnRemove' type='button' value='Remove' "
							 + "class='btnRemove btn btn-danger' data-paymentid='" + billID + "'></td></tr>";
				 } 
				 
				con.close(); 
				
				     // Complete the html table
				     output += "</table>"; 
				 } 
				 
				catch (Exception e) 
				 { 
					 output = "Error while reading the payment details."; 
					 System.err.println(e.getMessage()); 
				 } 
				
				
				return output; 
			}

	// Update buyers in the table
	public String updatePayment(String billID, String billName, String billAmount, String billDate, String NoOfTunits, String BillAr)
			{ 
				 String output = ""; 
				 try
				 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for updating."; 
					 
				 } 
				 // create a prepared statement
				 String query = "UPDATE bill SET billName=?,billAmount=?,billDate=?,NoOfTunits=?,BillAr=? WHERE billID=? ";
					
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 preparedStmt.setString(1, billName); 
				 preparedStmt.setString(2, billAmount); 
				 preparedStmt.setString(3, billDate); 
				 preparedStmt.setString(4, NoOfTunits); 
				 preparedStmt.setString(5, BillAr); 
				 preparedStmt.setInt(9, Integer.parseInt(billID)); 
				 
				 // execute the statement
				    preparedStmt.execute(); 
				    con.close(); 
				    String newPayment = readPayment(); 
					 output = "{\"status\":\"success\", \"data\": \"" + 
					 newPayment + "\"}"; 
					 
				   // output = "Payment Updated successfully"; 
				 } 
				 
				 catch (Exception e) 
				 { 
					 output = "{\"status\":\"error\", \"data\": \"Error while Updating the Payment.\"}"; 
				     //output = "Error while updating the payment details."; 
				     System.err.println(e.getMessage()); 
				 } 
				 
				 return output; 
				 }

	// Delete buyer in the table
	public String deletePayment(String billID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from bill where billID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(billID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			  String newPayment = readPayment(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 newPayment + "\"}"; 
		//	output = "payment details Deleted successfully";

		} catch (Exception e) {
			 output = "{\"status\":\"error\", \"data\": \"Error while Deleting the Payment.\"}"; 
		//	output = "Error while deleting the payment details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}

