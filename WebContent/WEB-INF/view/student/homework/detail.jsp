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
<title>�γ̽���</title>
<%@include file="../../common/head.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/course.js"></script>
</head>
<body>
	

	<div class="w1000">

		<jsp:include page="../../common/navigation_menu.jsp" />


		<div class="cont">
			<div class="current-position">
				<span class="font14"> 当前位置<a href="${pageContext.request.contextPath }/">首页</a> &gt;&gt; 
					<a href="${pageContext.request.contextPath }/student/course"> 课程</a> &gt;&gt; 课件</span>
					
			</div>

			<div class="repair_con mt10">
				<div class="repair_title">
					<ul>
					    <li  class="current cur" onclick="go('${pageContext.request.contextPath }/homework/detail/${homeworkId }')">作业详情</li>																					
					</ul>		
					<span class="back">
						<a href="${from_url eq null ? pageContext.request.contextPath : from_url }">&lt;&lt;返回</a></span>
				</div>
            <div class="repair_main">
            				
					<div class=" Re-cont my-repair">		
						<dl>	
							<dt>作业名称:</dt>
							<dd>
							${homework.name }
							</dd>
						</dl>												
						<dl>
							<dt>作业介绍 :</dt>
							<dd>
								${homework.introduction }
							</dd>
						</dl>
						
					   <dl>
							<dt>此次作业分数</dt>
							<dd>
								${homework.score}
							</dd>
						</dl>
						
						</div>				
								<div class="repair_info">
             			<ul>
	             			<li><a href="javascript:void(0);" onclick="addCourseware('${courseId }')">上传作业</a></li>                               	                	                
                		</ul>
             		</div>
			
		</div>
		</div>
	</div>
	<jsp:include page="../../common/footer.jsp" />
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