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
Map<Integer,String> m = db.getExamIdName();
%>
<%
 Teacher te = (Teacher)session.getAttribute("tdetail");
if(te == null){ 
	
	response.sendRedirect("teacher_login.jsp");
}

%>
<form action="Update_question" method="post">
Ques Id :<h4><%=request.getParameter("qid")%></h4>
<input name="examId" type="hidden" value="<%=request.getParameter("examId")%>">
<input name="qid" type="hidden" value="<%=request.getParameter("qid")%>">
<label>Update Question </label><br>
<textarea row="2"  name="ques" ><%=request.getParameter("question")%></textarea><br>
<label>Option 1</label>
<input type="text" name="op1" value="<%=request.getParameter("op1")%>"><br>
<label>Option 2</label>

<input type="text" name="op2" value="<%=request.getParameter("op2")%>"><br>
<label>Option 3</label>

<input type="text" name="op3" value="<%=request.getParameter("op3")%>"><br>
<label>Option 4</label>

<input type="text" name="op4" value="<%=request.getParameter("op4")%>"><br>
<label>choose correct option</label>
<select name="coption" id="coption">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
</select>
<script type="text/javascript">
document.getElementById("coption").value="<%=request.getParameter("copt")%>"
</script>
<button type="submit">submit</button>
</form>
</body>
</html>