import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseProvider {
	public static Connection initiateConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//returns the object for the given class
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/src","root","root");
			System.out.println("Connected to database");
			return connection;
		}catch(ClassNotFoundException e) {
			System.out.println("ClassNotFoundException occured");
			return null;
		}catch(SQLException e) {
			System.out.println("SQLException occurred");
			return null;
		}
	}
}
