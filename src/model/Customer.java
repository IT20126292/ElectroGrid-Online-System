package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {

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

	public String insertCustomer(String cus_Name, String cus_Nic, String cus_addr, String cus_pnumber, String cus_email, String cus_pwd) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into customer(`cID`,`cus_Name`,`cus_Nic`,`cus_addr`,`cus_pnumber`,`cus_email`,`cus_pwd`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cus_Name);
			preparedStmt.setString(3, cus_Nic);
			preparedStmt.setString(4, cus_addr);
			preparedStmt.setString(5, cus_pnumber);
			preparedStmt.setString(6, cus_email);
			preparedStmt.setString(7, cus_pwd);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "New User Created Successfully !";
		} catch (Exception e) {
			output = "Error while inserting the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readCustomer() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>ID</th><th>Customer Name</th><th>Customer NIC</th><th>Customer Address</th><th>Customer PhoneNumber</th><th>Customer Email</th><th>Customer Pass</th></tr>";
			String query = "select * from customer";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String cID = Integer.toString(rs.getInt("cID"));
				String cus_Name = rs.getString("cus_Name");
				String cus_Nic = rs.getString("cus_Nic");
				String cus_addr = rs.getString("cus_addr");
				String cus_pnumber = rs.getString("cus_pnumber");
				String cus_email = rs.getString("cus_email");
				String cus_pwd = rs.getString("cus_pwd");

				// Add into the html table
				output += "<tr><td>" + cID + "</td>";
				output += "<td>" + cus_Name + "</td>";
				output += "<td>" + cus_Nic + "</td>";
				output += "<td>" + cus_addr + "</td>";
				output += "<td>" + cus_pnumber + "</td>";
				output += "<td>" + cus_email + "</td>";
				output += "<td>" + cus_pwd + "</td>";
				
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

	public String updateCustomer(String cID, String cus_Name, String cus_Nic, String cus_addr, String cus_pnumber, String cus_email, String cus_pwd) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE customer SET cus_Name=?,cus_Nic=?,cus_addr=?,cus_pnumber=?,cus_email=?,cus_pwd=?" + "WHERE cID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, cus_Name);
			preparedStmt.setString(2, cus_Nic);
			preparedStmt.setString(3, cus_addr);
			preparedStmt.setString(4, cus_pnumber);
			preparedStmt.setString(5, cus_pwd);
			preparedStmt.setString(6, cus_pwd);
			preparedStmt.setInt(7, Integer.parseInt(cID));

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

	public String deleteCustomer(String cID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from customer where cID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(cID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "User Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the user.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
