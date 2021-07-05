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
if(te!=null){ %>
welcome <%=te.getName() %> ! How are You ?
login sucessfully!

	<%}
else
	response.sendRedirect("teacher_login.jsp");
%>
<a href="create_exam.jsp">CREATE EXAM</a>
<a href="add_question.jsp">ADD QUESTION</a>
<a href="view_question.jsp">VIEW QUESTION</a>

<form action="Teacherlogout" >
<button type="submit">Logout</button>
</form>
</body>
</html>