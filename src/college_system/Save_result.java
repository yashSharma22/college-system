package college_system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import database.Exam;
import database.Question;
import database.Student;

/**
 * Servlet implementation class Save_result
 */
@WebServlet("/Save_result")
public class Save_result extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sse = request.getSession(false);// false se purane seddion ko check krta h
		Student st = (Student) sse.getAttribute("sdetail");
		if (st == null) {
			response.sendRedirect("student_login.jsp");
			return;
		}
		ServletContext sc = getServletContext();
		Database db = (Database) sc.getAttribute("dob");
		Exam ex = (Exam) sse.getAttribute("examdetails");
		ArrayList<Question> aq = (ArrayList<Question>) sse.getAttribute("examques");
		String allans[] = request.getParameterValues("user-answers");
		int canscount = 0, incanscount = 0;
		for (int i = 0; i < aq.size(); i++) {
			if (!allans[i].equals("")) {
				if (Integer.parseInt(allans[i]) == aq.get(i).getCoption()) {
					canscount++;
				} else {
					incanscount++;
				}
			}
		}
		db.saveResult(st.getSid(), st.getDeptid(), canscount, incanscount, ex);

		int result = canscount * ex.getPmarks() - incanscount * ex.getNmarks();
		int unattemped = ex.getNoOfQues() - (canscount + incanscount);

		String rmsg = "Correct Questions = "+ canscount + "<br>" +
				"Incorrect Questions = " + incanscount + "<br>" +
				"Unattempted Questions = " + unattemped;
		
		request.setAttribute("msg", "Result = "+result+"/"+ex.getNoOfQues()*ex.getPmarks());
		request.setAttribute("msg2", rmsg);
		request.setAttribute("link", "student_option.jsp");
		request.setAttribute("bname", "Continue");
		request.setAttribute("color", "success");
		request.getRequestDispatcher("msg.jsp").forward(request, response);
	}

}
