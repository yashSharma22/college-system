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
 * Servlet implementation class Deleteques
 */
@WebServlet("/Deleteques")
public class Deleteques extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deleteques() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int examid,qid;
	examid=Integer.parseInt(request.getParameter("examId"));
	qid = Integer.parseInt(request.getParameter("qid"));
	ServletContext sc = getServletContext();
	Database db = (Database)sc.getAttribute("dob");
	if(db.deleteQuestio(examid,qid)) {
		System.out.println("question deleted");
		response.sendRedirect("update_question.jsp?examId="+examid);
	}
	}

	

}
