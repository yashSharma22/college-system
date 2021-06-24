<%@page import="java.util.Map"%>
<%@page import="college_system.Teacher"%>
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
Map<Integer,String> m = db.getExamIdName();
%>
<%
 Teacher te = (Teacher)session.getAttribute("tdetail");
if(te == null){ 
	response.sendRedirect("teacher_login.jsp");
}

%>
<form action="Add_question" method="post">
<select name="examid"><% for(Map.Entry<Integer,String> mp: m.entrySet())
	{%>
		 <option value="<%=mp.getKey() %>"><%=mp.getValue() %></option>
	<% }%>
	
</select><br><br>
<textarea row="2"  name="ques"></textarea><br>
<label>Option 1</label>
<input type="text" name="op1"><br>
<label>Option 2</label>

<input type="text" name="op2"><br>
<label>Option 3</label>

<input type="text" name="op3"><br>
<label>Option 4</label>

<input type="text" name="op4"><br>
<label>choose correct option</label>
<select name="coption">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
</select>
<button type="submit">submit</button>
</form>
</body>
</html>