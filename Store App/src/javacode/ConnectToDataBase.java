package javacode;

import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConnectToDataBase {

	
	private static java.sql.Connection con;
	private static Statement s;

	public static Statement getS() {
		return s;
	}

	public static void setS(Statement s) {
		ConnectToDataBase.s = s;
	}

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:ORCL","system","orcl");
			s = con.createStatement();
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null,
					"error " + e.getMessage(), "error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
