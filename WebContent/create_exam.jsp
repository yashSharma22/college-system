<%@page import="college_system.Teacher"%>
<%@page import="java.util.Map"%>
<%@page import="database.Database"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //http 1.1
response.setHeader("Pragma", "no-cache"); //http1.0
response.setHeader("Expires", "0"); //proxies %>
<%Database db = (Database)application.getAttribute("dob");
Map<Integer,String> m = db.getDepartment();
%>
<%
 Teacher te = (Teacher)session.getAttribute("tdetail");
if(te == null){ 
	response.sendRedirect("teacher_login.jsp");
}

%>
<form action="Create_exam">
<label>Exam name: </label>
<input type="text" name="examName">
<label>Date</label>
<input type="datetime" name="dateTime">
<label>Department</label>
<select><% for(Map.Entry<Integer,String> mp: m.entrySet())
	{%>
		 <option value="<%=mp.getKey() %>"><%=mp.getValue() %></option>
	<% }%>
	
</select>
<label>No. of question</label>
<input type="number" name="noOfQues">
<label>Positive marking:</label>
<input type="number" name="pmarks">
<label>Negative marking:</label>
<input type="number" name="nmarks">
</form>
</body>
</html>