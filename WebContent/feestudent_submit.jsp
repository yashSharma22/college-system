<%@page import="college_system.Fee"%>
<%@page import="database.Database"%>
<%@page import="college_system.Student"%>
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
Database db= (Database)application.getAttribute("dob"); 
Student stu = db.studentDetail(Integer.parseInt(request.getParameter("fsid")));
session.setAttribute("studentdetail", stu);
if(stu!=null){%>
welcome <%=stu.getName()%>!<br>
Dept. Id :<%=stu.getDeptid()%><br>
Student Id :<%=stu.getSid()%><br>
Student MobNO. :<%=stu.getMobno()%><br>
Student EmailId: <%=stu.getEmail()%>
Student Totalfee :<%=stu.getTotalfees() %><br>
Student Submitedfee :<%=stu.getSubmitedfees() %><br>
Remaining fee :<%=stu.getTotalfees()-stu.getSubmitedfees() %>
<%}
else
	response.sendRedirect("student_login.jsp");
 %>
 <br>
 <form action="Feesubmit" method="post">
 <input type="number" name="sfee" placeholder="Enter amount">
 <button type="submit">submit</button>
 </form>
</body>
</html>