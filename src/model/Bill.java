package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill {

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

	public String insertBill(String billName, String billAmount, String billDate, String NoOfTunits, String BillAr) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into bill(`billID`,`billName`,`billAmount`,`billDate`,`NoOfTunits`,`BillAr`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, billName);
			preparedStmt.setString(3, billAmount);
			preparedStmt.setString(4, billDate);
			preparedStmt.setString(5, NoOfTunits);
			preparedStmt.setString(6, BillAr);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readBill() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>ID</th><th>Bill Name</th><th>Bill Amount</th><th>Bill Date</th><th>Units</th><th>Bill AR</th></tr>";
			String query = "select * from bill";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String billID = Integer.toString(rs.getInt("billID"));
				String billName = rs.getString("billName");
				String billAmount = rs.getString("billAmount");
				String billDate = rs.getString("billDate");
				String NoOfTunits = rs.getString("NoOfTunits");
				String BillAr = rs.getString("BillAr");

				// Add into the html table
				output += "<tr><td>" + billID + "</td>";
				output += "<td>" + billName + "</td>";
				output += "<td>" + billAmount + "</td>";
				output += "<td>" + billDate + "</td>";
				output += "<td>" + NoOfTunits + "</td>";
				output += "<td>" + BillAr + "</td>";
				
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

	public String updateBill(String billID, String billName, String billAmount, String billDate, String NoOfTunits, String BillAr) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE bill SET billName=?,billAmount=?,billDate=?,NoOfTunits=?,BillAr=?" + "WHERE billID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, billName);
			preparedStmt.setString(2, billAmount);
			preparedStmt.setString(3, billDate);
			preparedStmt.setString(4, NoOfTunits);
			preparedStmt.setString(5, BillAr);
			preparedStmt.setInt(6, Integer.parseInt(billID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the customer.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteBill(String billID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from custom where billID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(billID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Bill.";
			System.err.println(e.getMessage());
		}

		return output;
	}

//	public String readBill() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
