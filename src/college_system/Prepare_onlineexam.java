package college_system;

import java.io.IOException;
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
 * Servlet implementation class Prepare_onlineexam
 */
@WebServlet("/Prepare_onlineexam")
public class Prepare_onlineexam extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sse = request.getSession(false);//false se purane seddion ko check krta h 
		Student st = (Student) sse.getAttribute("sdetail");
		ServletContext sc = getServletContext();
		Database db = (Database)sc.getAttribute("dob");
		if(st==null) {
			response.sendRedirect("student_login.jsp");
		}
		Exam ex = (Exam) sse.getAttribute("examdetails");
		ArrayList<Question> qal = db.getExamQuestion(ex);
    
		sse.setAttribute("examques", qal);//it set different ques for different student
		response.sendRedirect("online_exam_quespage.jsp");
	}

	

}
