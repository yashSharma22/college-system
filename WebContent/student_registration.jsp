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
<form action="Student_registration">
 <label>ENTER YOUR NAME :</label>
    <input type="text" placeholder="enter your full name" name="name"> (max 30 characters a-z and A-Z)<br><br>
    
    <label>PASSWORD :</label>
    <input type="password" placeholder="enter your password" name="pass"><br><br>

    <label>CONFIRM PASSWORD :</label>
    <input type="password" placeholder="retype your password" name="cpass"><br><br>

    <label>EMAIL ID :</label>
    <input type="email" name="email"><br><br>
    
    <label>MOBILE NO. :</label>
    <input type="text" maxlength="10" name="mob"> (10 digit number)<br><br>
    
    <label>GENDER :</label>
    Male <input type="radio" name="gender" value="Male">
    Female <input type="radio" name="gender" value="Female"><br><br>
  
    <label>DATE OF BIRTH :</label>
    <input type="date" name="dob"><br><br>

    <label>QUALIFICATION :</label>
    <br>
    <label>Enter your 10th % :</label>
    <input type="number" placeholder="Enter your 10th %" name="10%">

	<label>Enter your 12th % :</label>
    <input type="number" placeholder="Enter your 12th %" name="12%"><br><br>
	
    
   
    
    <label>SELECT DEPARTMENT:</label>
	
	<% for(Map.Entry<Integer,String> mp: m.entrySet())
	{%>
		<%=mp.getValue() %> <input type="radio" name="deptid" value=<%=mp.getKey() %>>
	<% }%>
    
    <button type="submit">Submit</button>
</form>
</body>
</html>