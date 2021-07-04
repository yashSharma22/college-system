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
 * Servlet implementation class Feesubmit
 */
@WebServlet("/Feesubmit")
public class Feesubmit extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int amount = Integer.parseInt(request.getParameter("sfee"));
		HttpSession fe = request.getSession(false);//false se purane seddion ko check krta h 
		Student st = (Student) fe.getAttribute("studentdetail");
		
		int sid = st.getSid();
		int rfee = st.getTotalfees() - amount;
		System.out.println(rfee);
		if(amount < rfee) {
			ServletContext sc = getServletContext();
			Database db = (Database)sc.getAttribute("dob");
			if(st!=null) {
				if(db.feesubmit(amount+st.getSubmitedfees(),sid))
				{
					
					response.getWriter().print("fee submited");
				}
			}
		}
		else {
			response.getWriter().print( "<a href=\"feestudent_submit.jsp?fsid="+sid+"\">feesubmit</a>");

		}
		
	}

}
