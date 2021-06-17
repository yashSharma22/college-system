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
 * Servlet implementation class Feeadmin_login
 */
@WebServlet("/Feeadmin_login")
public class Feeadmin_login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fid = Integer.parseInt(request.getParameter("fid"));
		String pass= request.getParameter("pass");
		ServletContext sc = getServletContext();
		Database db = (Database)sc.getAttribute("dob");
		if(db.feeadminlogin(fid,pass)) {
			Fee fd= db.feeadminDetail();
			HttpSession fe = request.getSession();
			fe.setAttribute("fdetail", fd);
			response.sendRedirect("feeadmin_option.jsp");
		}
		else {
			response.sendRedirect("feeadmin_login.jsp");
		}
	}

}
