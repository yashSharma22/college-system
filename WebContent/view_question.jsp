<%@page import="java.sql.ResultSet"%>
<%@page import="database.Database"%>
<%@page import="college_system.Exam"%>
<%@page import="college_system.Teacher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //http 1.1
response.setHeader("Pragma", "no-cache"); //http1.0
response.setHeader("Expires", "0"); //proxies
%>
<%
 Teacher te = (Teacher)session.getAttribute("tdetail");
if(te==null){ 
	response.sendRedirect("teacher_login.jsp");
}%>
<%!ResultSet rs; %>
<%Exam ex = new Exam();
ServletContext sc = getServletContext();
Database db = (Database)sc.getAttribute("dob");
rs = db.selectExam();
%>
<table border="2">
<tr>
<th>ExamID</th>
<th>ExamName</th>
<th>ExamDate</th>
<th>ExamStartTime</th>
<th>Total Ques</th>
<th>Positive marks</th>
<th>Negative Marks</th>
<th>Duration Time</th>
<th colspan="2">Update DEtails</th>
</tr>
<%while(rs.next()){ %>
<tr>
<td><%=rs.getInt(1) %></td>
<td><%=rs.getString(2) %></td>
<td><%=rs.getDate(4) %></td>
<td><%=rs.getTime(5) %></td>
<td><%=rs.getInt(6) %></td>
<td><%=rs.getInt(7) %></td>
<td><%=rs.getInt(8) %></td>
<td><%=rs.getInt(9) %></td>
<td><a href="update_exam.jsp?examId=<%=rs.getInt(1)%>&examName=<%=rs.getString(2)%>&did=<%=rs.getInt(3) %>&examDate=<%=rs.getDate(4)%>&startTime=<%=rs.getTime(5)%>&totalQues=<%=rs.getInt(6)%>&pmarks=<%=rs.getInt(7)%>&nmarks=<%=rs.getInt(8)%>&dTime=<%=rs.getInt(9)%>">Update details</a>
<td><a href="update_question.jsp?examId=<%=rs.getInt(1)%>">Update Question</a>

</tr>
<%} %>


</table>

	
</body>
</html>