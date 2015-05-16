<%
request.setAttribute("CURRENTUSER", request.getSession().getAttribute("user"));
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/init.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 

"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加课程</title>
<%@include file="../../common/head.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/course.js"></script>
<script type="text/javascript">
    var editor = null; 
</script>
</head>
<body>
<div id="body_container">	

	<div class="w1000">

		<jsp:include page="../../common/teacher_navigation_menu.jsp" />


		<div class="cont">
			<div class="current-position">
				<span class="font14"> 当前位置：<a href="${pageContext.request.contextPath }/teacher">首页</a> &gt;&gt; 
					<a href="${pageContext.request.contextPath }/teacher/course"> 课程</a> &gt;&gt; 课程添加 </span>
			</div>

			<div class="repair_con mt10">
				<div class="repair_title">
					<ul>
						<li class="current cur">课程${empty id?申请:修改 }</li>
					</ul>
					<span class="back">
						<a href="${from_url eq null ? pageContext.request.contextPath : from_url }">&lt;&lt;返回</a></span>
				</div>
            <div class="repair_main">
            
				<form id="apply"
					action="${pageContext.request.contextPath}/teacher/course/add"
					method="post">
					<c:if test="${not empty id }">
					<input type="hidden" name="courseId"  value="${id }" />
					</c:if>
					<input type="hidden" name="teacherId"  value="${CURRENTUSER.userId }" />
					<input type="hidden" name="teacherName"  value="${CURRENTUSER.userNo }" />
					<div class=" Re-cont my-repair">
						<p class="message-help " style="margin-top: 10px;">
							带<font style="font-size: 16px; color: red;">*</font>号的为必填项
						</p>
						<dl>
						<dt>申请工号 :</dt>
							<dd>
							<label>${CURRENTUSER.userNo }</label>
							</dd>
						</dl>
						
						<dl>	
							<dt>课程名称 :</dt>
							<dd>
							<input  name="courseName" placeholder="请在此输入有效课程名称"
									class="repair-text" style="padding: 4px 0"
									type="text" value="${course.courseName }"/>
							</dd>
						</dl>												
						<dl>
							<dt>课程介绍 :</dt>
							<dd>
								<textarea name="courseIntr" id="courseIntr"  placeholder="请在此输入备注信息">${course.courseIntr }</textarea>
								<script type="text/javascript">editor=CKEDITOR.replace('courseIntr',{height:300});</script>
							</dd>
						</dl>
						<div class="submit-btn" style="margin-left: 86px;">
							<a href="javascript:void(0);" onclick="applySubmit();">提交</a>
						</div>
						</div>
				</form>
			
		</div>
		</div>
	</div>
	<jsp:include page="../../common/footer.jsp" />
</div>
</div>
<script>
	function applySubmit() {		
		if (true) {
			 editor.updateElement();
			var parm = $("#apply").serialize();
			postAjaxRequest(basePath()+"/teacher/course/add",
					parm, function(msg) {
						if (msg.status == true) {
							alert(msg.message);
							goAfterTime(basePath() + "/teacher/course",1000);							
						} else {
							alert(msg.message);
						}

					});
		}
		}
</script>
</body>
</html>
