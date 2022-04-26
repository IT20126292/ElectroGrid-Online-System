package model;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electri?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayment(String p_status, String p_date, String p_crdnumber, String p_cvv, String p_amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting payment Details.";
			}
			// create a prepared statement
			String query = " insert into payment(`p_ID`,`p_status`,`p_date`,`p_crdnumber`,`p_cvv`,`p_amount`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, p_status);
			preparedStmt.setString(3, p_date);
			preparedStmt.setString(4, p_crdnumber);
			preparedStmt.setString(5, p_cvv);
			preparedStmt.setString(6, p_amount);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Payment Details Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>ID</th><th>p_status</th><th>p_date</th><th>p_crdnumber</th><th>p_cvv</th><th>p_amount</th></tr>";
			String query = "select * from payment";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String p_ID = Integer.toString(rs.getInt("p_ID"));
				String p_status = rs.getString("p_status");
				String p_date = rs.getString("p_date");
				String p_crdnumber = rs.getString("p_crdnumber");
				String p_cvv = rs.getString("p_cvv");
				String p_amount = rs.getString("p_amount");

				// Add into the html table
				output += "<tr><td>" + p_ID + "</td>";
				output += "<td>" + p_status + "</td>";
				output += "<td>" + p_date + "</td>";
				output += "<td>" + p_crdnumber + "</td>";
				output += "<td>" + p_cvv + "</td>";
				output += "<td>" + p_amount + "</td>";
				
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayment(String p_ID, String p_status, String p_date, String p_crdnumber, String p_cvv, String p_amount) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE payment SET p_status=?,p_date=?,p_crdnumber=?,p_cvv=?,p_amount=?" + "WHERE p_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, p_status);
			preparedStmt.setString(2, p_date);
			preparedStmt.setString(3, p_crdnumber);
			preparedStmt.setString(4, p_cvv);
			preparedStmt.setString(5, p_amount);
			preparedStmt.setInt(6, Integer.parseInt(p_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Payment Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Payment.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deletePayment(String p_ID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from payment where p_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(p_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Payment.";
			System.err.println(e.getMessage());
		}

		return output;
	}

//	public String readBill() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
