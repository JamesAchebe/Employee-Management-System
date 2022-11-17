import java.sql.*;
import javax.swing.*;


public class EmployeeData {
	
	public static Connection ConnectDB()
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\eclipse\\workspace\\EmployeeData\\employee.db");
					JOptionPane.showMessageDialog(null, "Connetion Made");
					return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Connetion Error");
			return null;
		}
	}
	
	
}
