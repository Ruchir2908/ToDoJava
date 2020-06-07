import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

	 public boolean validate(LoginHelper helper) throws ClassNotFoundException {
	        boolean status = false;

	        Class.forName("com.mysql.jdbc.Driver");

	        try (Connection connection = JDBCUtils.getConnection();
	            PreparedStatement preparedStatement = connection
	            .prepareStatement("select * from users where username = ? and password = ? ")) {
	            preparedStatement.setString(1, helper.getUsername());
	            preparedStatement.setString(2, helper.getPassword());

	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            status = rs.next();

	        } catch (SQLException e) {
	            JDBCUtils.printSQLException(e);
	        }
	        return status;
	 }
	
}
