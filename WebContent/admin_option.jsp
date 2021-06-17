
<%@page import="college_system.Admin"%>

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
 Admin ad = (Admin)session.getAttribute("detail");
if(ad!=null){ %>
welcome <%=ad.getName() %> ! How are You ?
login sucessfully!
<br><br>

	<%}
else
	response.sendRedirect("admin_login.jsp");
%>
${ad.getName()}
<a href="teacher_registration.jsp">Teacher Registration</a>
<form action="Logout">

<button type="submit">logout</button>
</form>
</body>
</html>