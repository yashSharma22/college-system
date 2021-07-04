<%@page import="database.Database"%>
<%@page import="college_system.Fee"%>
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
 Fee fe = (Fee)session.getAttribute("fdetail");
if(fe==null){ 
	response.sendRedirect("feeadmin_login.jsp");
}
%>
<%
Database db = (Database)application.getAttribute("dob");

%>
<form action="feestudent_submit.jsp" >
Enter student id: <input type="number" placeholder="Enter student id" name="fsid"><br>
<button type="submit">submit</button>
</form>


<form action="Feeadmin_logout">

<button type="submit">logout</button>
</body>
</html>