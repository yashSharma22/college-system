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
 * Servlet implementation class Update_question
 */
@WebServlet("/Update_question")
public class Update_question extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		Database db = (Database)sc.getAttribute("dob");
		Question q = new Question();
		int examId = Integer.parseInt(request.getParameter("examId"));
		 q.setQues(request.getParameter("ques"));
		 q.setOp1(request.getParameter("op1"));
		 q.setOp2(request.getParameter("op2"));
		 q.setOp3(request.getParameter("op3"));
		 q.setOp4(request.getParameter("op4"));
		 q.setCoption(Integer.parseInt(request.getParameter("coption")));
		 q.setQid(Integer.parseInt(request.getParameter("qid")));
		// System.out.println(Integer.parseInt(request.getParameter("qid")));
		 if(db.updateQues(examId, q)) {
//			 System.out.println("Question updated");
//			 System.out.println(Integer.parseInt(request.getParameter("qid")));

			response.getWriter().print("<a href=\"update_question.jsp?examId="+examId+"\">update more question</a>");
		 }
	}

}
