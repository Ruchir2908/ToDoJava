

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class ToDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ToDoDAO todoDao;
	
	public void init() {
		todoDao = new ToDoDAOUser();
	}
       
    public ToDoController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getServletPath();
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertToDo(request, response);
                    break;
                case "/delete":
                    deleteToDo(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateToDo(request, response);
                    break;
                case "/list":
                    listToDo(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void listToDo(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		        List < ToDo > listTodo = todoDao.selectAllToDos();
		        request.setAttribute("listToDo", listTodo);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("todo-list.jsp");
		        dispatcher.forward(request, response);
		    }

		    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		        RequestDispatcher dispatcher = request.getRequestDispatcher("todo-form.jsp");
		        dispatcher.forward(request, response);
		    }

		    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
		        int id = Integer.parseInt(request.getParameter("id"));
		        ToDo existingTodo = todoDao.selectToDo(id);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("todo-form.jsp");
		        request.setAttribute("todo", existingTodo);
		        dispatcher.forward(request, response);

		    }

		    private void insertToDo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		        String title = request.getParameter("title");
		        String username = request.getParameter("username");
		        String description = request.getParameter("description");
		        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		        ToDo newTodo = new ToDo(title, username, description, LocalDate.now(), isDone);
		        todoDao.insertToDo(newTodo);
		        response.sendRedirect("list");
		    }

		    private void updateToDo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		        int id = Integer.parseInt(request.getParameter("id"));

		        String title = request.getParameter("title");
		        String username = request.getParameter("username");
		        String description = request.getParameter("description");
		        LocalDate targetDate = LocalDate.parse(request.getParameter("date"));

		        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		        ToDo updateTodo = new ToDo(id, title, username, description, targetDate, isDone);

		        todoDao.updateToDo(updateTodo);

		        response.sendRedirect("list");
		    }

		    private void deleteToDo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		        int id = Integer.parseInt(request.getParameter("id"));
		        todoDao.deleteToDo(id);
		        response.sendRedirect("list");
		    }

}
