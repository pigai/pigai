<%
request.setAttribute("CURRENTUSER", request.getSession().getAttribute("user"));
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/init.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../common/head.jsp"%>
<title>教师个人信息</title>
</head>
<body>
	
<div id="body_container"> 
	<div class="w1000">

		<jsp:include page="../common/teacher_navigation_menu.jsp" />
		
		<div class="cont">
			<div class="current-position">
				<span class="font14"> 当前位置：<a href="${pageContext.request.contextPath }/">首页</a> &gt;&gt; 
					<a href="${pageContext.request.contextPath }/student/course">个人信息</a></span>
					
			</div>

			<div class="repair_con mt10">
				<div class="repair_title">
					<ul>
					    <li  class="current cur" >个人信息</li>															
					</ul>		
					<span class="back">
						<a href="${from_url eq null ? pageContext.request.contextPath : from_url }">&lt;&lt;返回</a></span>
				</div>
            <div class="repair_main">
            				
					<table border="0" cellpadding="3" cellspacing="3" style="margin: 0 auto;margin-top:100px;"> 
				        <tr> 
				            <td style="text-align: right; padding: 10px;"> 
				                <label>工号:</label> 
				            </td> 
				            <td> 
				                <input id="teacherNo" type="text" size="20" style="border-style:none" value="${requestScope.teacher.teacherNo}" /> 
				            </td> 
				        </tr> 
				        <tr> 
				            <td style="text-align: right; padding: 10px"> 
				                <label>姓名:</label> 
				            </td> 
				            <td> 
				                <input id="name" type="text" size="20" value="${requestScope.teacher.name}"/> 
				            </td> 
				        </tr> 
				        <tr> 
				            <td style="text-align: right; padding: 10px"> 
				                <label>学校:</label> 
				            </td> 
				            <td> 
				                <input id="school" type="text" size="20" value="${requestScope.teacher.school}"/> 
				            </td> 
				        </tr>
				        <tr> 
				            <td style="text-align: right; padding: 10px"> 
				                <label>院系:</label> 
				            </td> 
				            <td> 
				                <input id="college" type="text" size="20" value="${requestScope.teacher.college}"/> 
				            </td> 
				        </tr> 
				    </table> 		
		</div>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp" />
</div>
</div>
</body>
</html>
