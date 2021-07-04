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
 * Servlet implementation class Add_question
 */
@WebServlet("/Add_question")
public class Add_question extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 Question q = new Question();
	 q.setQues(request.getParameter("ques"));
	 q.setOp1(request.getParameter("op1"));
	 q.setOp2(request.getParameter("op2"));
	 q.setOp3(request.getParameter("op3"));
	 q.setOp4(request.getParameter("op4"));
	 q.setCoption(Integer.parseInt(request.getParameter("coption")));
	 ServletContext sc = getServletContext();
		Database db = (Database)sc.getAttribute("dob");
	 if(db.addQues(Integer.parseInt(request.getParameter("examid")), q)) {
		 System.out.println("question add");
		response.getWriter().print("<a href=\"add_question.jsp\">add more question</a>");
	 }
	}

}
