package college_system;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;

/**
 * Servlet implementation class Abc
 */
@WebServlet("/Abc")
public class Abc extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int aid = Integer.parseInt(request.getParameter("aid"));
		String pass= request.getParameter("pass");
		ServletContext sc = getServletContext();
		Database db = (Database)sc.getAttribute("dob");
		if(db.adminlogin(aid,pass)) {
			Admin ad= db.adminDetail();
			HttpSession se = request.getSession();
			se.setAttribute("detail", ad);
			response.sendRedirect("admin_option.jsp");
		}
		else {
			response.sendRedirect("index.jsp");
		}
		
	}

	

}
