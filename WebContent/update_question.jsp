<%@page import="college_system.Exam"%>
<%@page import="college_system.Question"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.Database"%>
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
}
%>
<%

Database db = (Database)application.getAttribute("dob");
ArrayList<Question> arques = db.getAllQuestion(Integer.parseInt(request.getParameter("examId")));
%>
<table>
<thead>
<tr>
<th>SNo</th>
<th>Question id</th>
<th>Question</th>
<th>Op1</th>
<th>Op2</th>
<th>Op3</th>
<th>Op4</th>
<th>Correct Option</th>
<th>Update</th>
<th>Delete</th>
</tr>
</thead>
<%for(int i=0;i<arques.size();i++){ 
Question q = arques.get(i);
%>	
	<tr>
	<td><%=i+1 %></td>
	<td><%=q.getQid()%></td>
	<td><%=q.getQues() %></td>
	<td><%=q.getOp1() %></td>
		<td><%=q.getOp2() %></td>
		<td><%=q.getOp3() %></td>
		<td><%=q.getOp4() %></td>
	<td><%=q.getCoption() %></td>
	<td><a href="update_quesdetails.jsp?examId=<%=Integer.parseInt(request.getParameter("examId"))%>&qid=<%=q.getQid()%>&question=<%=q.getQues()%>&op1=<%=q.getOp1()%>&op2=<%=q.getOp2()%>&op3=<%=q.getOp3()%>&op4=<%=q.getOp4()%>&copt=<%=q.getCoption()%>">update</a></td>
	<td><a href="Deleteques?examId=<%=request.getParameter("examId")%>&qid=<%=q.getQid()%>">deleteques</a></td>
	</tr>
<%} %>
</table>
</body>
</html>