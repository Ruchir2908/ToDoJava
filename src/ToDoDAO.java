import java.sql.SQLException;
import java.util.List;
public interface ToDoDAO {

	void insertToDo(ToDo todo) throws SQLException;

	ToDo selectToDo(long todoId);

	 List<ToDo> selectAllToDos();

	 boolean deleteToDo(int id) throws SQLException;

	 boolean updateToDo(ToDo todo) throws SQLException;
	
}
