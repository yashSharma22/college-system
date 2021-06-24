package college_system;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;

/**
 * Servlet implementation class Teacher_registration
 */
@WebServlet("/Teacher_registration")
public class Teacher_registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Teacher_registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession se = request.getSession(false);
		Admin ad = (Admin)request.getAttribute("datail");
		if(ad==null) {
			response.sendRedirect("index.jsp");
		}
		Teacher t= new Teacher();
		t.setName(request.getParameter("tname"));
		t.setEmail(request.getParameter("email"));
		t.setPassword(request.getParameter("pass"));
		t.setDeptid(Integer.parseInt(request.getParameter("departmentid")));
		t.setMobileno(request.getParameter("mob"));
		ServletContext sc = getServletContext();
		Database db = (Database)sc.getAttribute("dob");
		if(db.addTeacher(t)) {
			response.getWriter().print("Register sucessfully");
			response.sendRedirect("admin_option.jsp");
		}
	}

}
