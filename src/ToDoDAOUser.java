import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class ToDoDAOUser implements ToDoDAO{

	private static final String INSERT_TODOS_SQL = "INSERT INTO todos"+"  (title, username, description, date,  is_done) VALUES " + " (?, ?, ?, ?, ?);";
	private static final String SELECT_TODO_BY_ID = "select id,title,username,description,date,is_done from todos where id =?";
	private static final String SELECT_ALL_TODOS = "select * from todos";
	private static final String DELETE_TODO_BY_ID = "delete from todos where id = ?;";
	private static final String UPDATE_TODO = "update todos set title = ?, username= ?, description =?, date =?, is_done = ? where id = ?;";
	
	public ToDoDAOUser() {}
	
	 @Override
	 public void insertToDo(ToDo todo) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODOS_SQL)) {
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getUsername());
            preparedStatement.setString(3, todo.getDescription());
            preparedStatement.setDate(4, JDBCUtils.getSQLDate(todo.getDate()));
            preparedStatement.setBoolean(5, todo.isStatus());
	        System.out.println(preparedStatement);
	        preparedStatement.executeUpdate();
	    } catch (SQLException exception) {
	        JDBCUtils.printSQLException(exception);
        }
     }
	 
	 @Override
	    public ToDo selectToDo(long todoId) {
	        ToDo todo = null;
	        try (Connection connection = JDBCUtils.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODO_BY_ID);) {
	            preparedStatement.setLong(1, todoId);
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                long id = rs.getLong("id");
	                String title = rs.getString("title");
	                String username = rs.getString("username");
	                String description = rs.getString("description");
	                LocalDate targetDate = rs.getDate("date").toLocalDate();
	                boolean isDone = rs.getBoolean("is_done");
	                todo = new ToDo(id, title, username, description, targetDate, isDone);
	            }
	        } catch (SQLException exception) {
	            JDBCUtils.printSQLException(exception);
	        }
	        return todo;
	    }
	 
	 @Override
	    public List < ToDo > selectAllToDos() {
	        List < ToDo > todos = new ArrayList < > ();
	        try (Connection connection = JDBCUtils.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TODOS);) {
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                long id = rs.getLong("id");
	                String title = rs.getString("title");
	                String username = rs.getString("username");
	                String description = rs.getString("description");
	                LocalDate targetDate = rs.getDate("date").toLocalDate();
	                boolean isDone = rs.getBoolean("is_done");
	                todos.add(new ToDo(id, title, username, description, targetDate, isDone));
	            }
	        } catch (SQLException exception) {
	            JDBCUtils.printSQLException(exception);
	        }
	        return todos;
	    }

	    @Override
	    public boolean deleteToDo(int id) throws SQLException {
	        boolean rowDeleted;
	        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_TODO_BY_ID);) {
	            statement.setInt(1, id);
	            rowDeleted = statement.executeUpdate() > 0;
	        }
	        return rowDeleted;
	    }

	    @Override
	    public boolean updateToDo(ToDo todo) throws SQLException {
	        boolean rowUpdated;
	        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_TODO);) {
	            statement.setString(1, todo.getTitle());
	            statement.setString(2, todo.getUsername());
	            statement.setString(3, todo.getDescription());
	            statement.setDate(4, JDBCUtils.getSQLDate(todo.getDate()));
	            statement.setBoolean(5, todo.isStatus());
	            statement.setLong(6, todo.getId());
	            rowUpdated = statement.executeUpdate() > 0;
	        }
	        return rowUpdated;
	    }

	
}
