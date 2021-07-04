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
<form action="Update_exam" method="post">
  <h4><%=request.getParameter("examId") %></h4><br> 
<input name="eid" type="hidden" value="<%=request.getParameter("examId") %>">
<label>Exam name: </label>
<input type="text" name="examName" value="<%=request.getParameter("examName") %>"><br><br>
<label>Date</label>
<input type="date" name="date" value="<%=request.getParameter("examDate") %>"><br><br>
<label>Start Time:</label>
<input type="time" name="time" value="<%=request.getParameter("startTime") %>"><br><br>
<label>Exam Duration time</label>
<input type="number" name="dtime" value="<%=request.getParameter("dTime") %>"><br><br>
<label>Department</label>
<select id="deptid" name="deptid"><% for(Map.Entry<Integer,String> mp: m.entrySet())
	{%>
		 <option value="<%=mp.getKey() %>" ><%=mp.getValue() %></option>
	<% }%>
	
</select><br><br>
<script type="text/javascript">
document.getElementById("deptid").value="<%=request.getParameter("did")%>"
</script>

<label>No. of question</label>
<input type="number" name="noOfQues" value="<%=request.getParameter("totalQues") %>"><br><br>
<label>Positive marking:</label>
<input type="number" name="pmarks" value="<%=request.getParameter("pmarks") %>"><br><br>
<label>Negative marking:</label>
<input type="number" name="nmarks" value="<%=request.getParameter("nmarks") %>"><br><br>
<button type="submit">submit</button><br><br>
</form>
</body>
</html>