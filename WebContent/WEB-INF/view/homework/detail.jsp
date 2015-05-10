<%
request.setAttribute("CURRENTUSER", request.getSession().getAttribute("user"));
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/init.jsp"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 

"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程介绍</title>
<%@include file="../common/head.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/course.js"></script>
</head>
<body>
	

	<div class="w1000">

		<jsp:include page="../common/navigation_menu.jsp" />


		<div class="cont">
			<div class="current-position">
				<span class="font14"> 当前位置：<a href="${pageContext.request.contextPath }/">首页</a> &gt;&gt; 
					<a href="${pageContext.request.contextPath }/course"> 课程</a> &gt;&gt; 课程介绍 </span>
					
			</div>

			<div class="repair_con mt10">
				<div class="repair_title">
					<ul>
						<li class="current cur">作业详情</li>					
					</ul>
					<span class="back">
						<a href="${from_url eq null ? pageContext.request.contextPath : from_url }">&lt;&lt;返回</a></span>
				</div>
            <div class="repair_main">
            				
					<c:if test="${empty id }">
					<input type="hidden" name="courseId"  value="${id }" />
					</c:if>
					<input type="hidden" name="teacherId"  value="${CURRENTUSER.userId }" />
					<input type="hidden" name="teacherName"  value="${CURRENTUSER.userNo }" />
					<div class=" Re-cont my-repair">
						<p class="message-help " style="margin-top: 10px;">
							带<font style="font-size: 16px; color: red;">*</font>号的为必填项
						</p>
						<dl>
						<dt>老师名称:</dt>
							<dd>
							<label>${CURRENTUSER.userNo }</label>
							</dd>
						</dl>
						
						<dl>	
							<dt>课程名称 :</dt>
							<dd>
							${course.courseName }
						</dl>												
						<dl>
							<dt>课程介绍 :</dt>
							<dd>
								<textarea name="courseIntr"  placeholder="请在此输入备注信息">${course.courseIntr}</textarea>
							</dd>
						</dl>
						
						</div>				
			
		</div>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp" />
</div>

</body>
</html>
<script>
	function applySubmit() {		
		if (true) {
			var parm = $("#apply").serialize();
			postAjaxRequest(basePath()+"/course/add",
					parm, function(msg) {
						if (msg.status == true) {
							alert(msg.message);
							goAfterTime(basePath() + "/course",1000);							
						} else {
							alert(msg.message);
						}

					});
		}
		}
</script>