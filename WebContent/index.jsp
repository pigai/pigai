<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>入口</title>
</head>
<body>
<%--     <jsp:forward page="/WEB-INF/view/student/login.jsp"/>   --%>
<%
  response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
/*   String newLocn = "student/login"; */
	String newLocn = "home/homepage";
  response.setHeader("Location",newLocn);
%>
</body>
</html>