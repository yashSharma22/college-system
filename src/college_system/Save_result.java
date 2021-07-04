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

/**
 * Servlet implementation class Save_result
 */
@WebServlet("/Save_result")
public class Save_result extends HttpServlet {
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sse = request.getSession(false);//false se purane seddion ko check krta h 
		Student st = (Student) sse.getAttribute("sdetail");
		ServletContext sc = getServletContext();
		Database db = (Database)sc.getAttribute("dob");
		if(st==null) {
			response.sendRedirect("student_login.jsp");
		}
		Exam ex = (Exam) sse.getAttribute("examdetails");
		ArrayList<Question> aq = (ArrayList<Question>) sse.getAttribute("examques");
		String allans[] =  request.getParameterValues("user-answers");
		int canscount = 0,incanscount=0;
		for(int i=0;i<aq.size();i++) {
			if(!allans[i].equals("")) {
				if(Integer.parseInt(allans[i])==aq.get(i).getCoption()) {
					canscount++;
				}
				else {
					incanscount++;
				}
			}
		}
		db.saveResult(st.getSid(), ex.getExamid(), canscount, incanscount);
		PrintWriter out = response.getWriter();
		int result = canscount*ex.getPmarks() - incanscount*ex.getNmarks();
		out.println("Result="+ result);
		out.println("correct Question="+canscount);
		out.println("incorrect Question="+incanscount);
		int unattemped =ex.getNoOfQues()-(canscount+incanscount);
		out.println("uattemped Question="+ unattemped);
	}

}
