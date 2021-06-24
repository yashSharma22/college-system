<%@page import="college_system.Student"%>
<%@page import="college_system.Exam"%>
<%@page import="java.util.ArrayList"%>
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
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //http 1.1
response.setHeader("Pragma", "no-cache"); //http1.0
response.setHeader("Expires", "0"); //proxies
%>
<%Student stu = (Student)session.getAttribute("sdetail");
if(stu!=null){%>
welcome <%=stu.getName() %>!
<%}
else
	response.sendRedirect("student_login.jsp");
 %>
 <%
 Database db = (Database)application.getAttribute("dob");
 int eid = Integer.parseInt(request.getParameter("eid"));
 Exam ex= db.getOnlineExamDetails(eid);
 %>
 <%=ex.getExamName() %><br>
  <%=ex.getNoOfQues() %><br>
   <%=ex.getDeptid() %><br>
	 <%=ex.getDtime() %><br>
	  <%=ex.getDate()%><br>
	   <%=ex.getTime() %><br>
	   <%=ex.getDtime() %><br>
	    <%=ex.getPmarks()%><br>
	     <%=ex.getNmarks() %><br>
	    
	   <%session.setAttribute("examdetails", ex); %>
	  <a href="Prepare_onlineexam">start exam</a>
	 
	  
 
</body>
</html>