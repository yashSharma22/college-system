<%@page import="college_system.Admin"%>
<%@page import="java.util.Map"%>
<%@page import="database.Database"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
label{
width:150px;
}
</style>
</head>
<body>

<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //http 1.1
response.setHeader("Pragma", "no-cache"); //http1.0
response.setHeader("Expires", "0"); //proxies %>
<%Database db = (Database)application.getAttribute("dob");
Map<Integer,String> m = db.getDepartment();

%>
<%
 Admin ad = (Admin)session.getAttribute("detail");
if(ad == null){ 
	response.sendRedirect("admin_login.jsp");
	}
	
%>
<form action="Teacher_registration">
<label for="name">Name:</label>
          <input type="text" placeholder="Eneter your full name" name="tname"><br><br>
<label>EMAIL ID :</label>
          <input type="email" name="email"><br><br>
    
<label>MOBILE NO. :</label>
          <input type="text" maxlength="10" name="mob"> (10 digit number)<br><br>

<label>PASSWORD :</label>
		  <input type="password" placeholder="Enter your password" name="pass"><br><br>
		 

<label>CONFERM PASSWORD :</label>
    	  <input type="password" placeholder="Re-type your password" name="cpass"><br><br>
<label>DEPARTMENT:</label>
	
	<% for(Map.Entry<Integer,String> mp: m.entrySet())
	{%>
		<%=mp.getValue() %> <input type="radio" name="departmentid" value=<%=mp.getKey() %>>
	<% }%>
	

   <br><br>
    <button type="submit">Submit</button>
</form>
</body>
</html>