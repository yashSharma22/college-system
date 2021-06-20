package college_system;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;

/**
 * Servlet implementation class Student_registration
 */
@WebServlet("/Student_registration")
public class Student_registration extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 Student stu = new Student();
	stu.setName(request.getParameter("name"));
	stu.setPassword(request.getParameter("pass"));
	stu.setEmail(request.getParameter("email"));
	stu.setMobno(request.getParameter("mob"));
	stu.setGender(request.getParameter("gender"));
	stu.setDob(request.getParameter("dob"));
	stu.setTenth(Integer.parseInt(request.getParameter("10%")));
	stu.setTwelfth(Integer.parseInt(request.getParameter("12%")));
	stu.setDeptid(Integer.parseInt(request.getParameter("deptid")));
	ServletContext sc = getServletContext();
	Database db = (Database)sc.getAttribute("dob");
	if(db.addStudent(stu)) {
		response.getWriter().print("Register sucessfully");
		response.sendRedirect("index.jsp");
	}
	
	}

}
