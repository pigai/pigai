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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/homework.js"></script>
</head>
<body>	
	<div class="w1000">

		<jsp:include page="../../common/navigation_menu.jsp" />

		<div class="cont">
			<div class="current-position">
				<span class="font14"> 当前位置：<a href="${pageContext.request.contextPath }/teacher">首页</a> &gt;&gt; 
					<a href="${pageContext.request.contextPath }/teacher/course"> 课程</a> &gt;&gt; 作业</span>
			</div>

			<div class="repair_con mt10">
				<div class="repair_title">
					<ul>
						<li class="current cur">作业${empty homeworkId?"添加":"修改" }</li>
					</ul>
					<span class="back">
						<a href="${from_url eq null ? pageContext.request.contextPath : from_url }">&lt;&lt;返回</a></span>
				</div>
            <div class="repair_main">            
				<form id="apply"
					action="${pageContext.request.contextPath}/teacher/homework/add"
					method="post">
					<input type="hidden" name="courseId" id="courseId" value="${courseId }" />	
					<input type="hidden" name="homeworkId" id="homeworkId"  value="${homeworkId }" />											
					<div class=" Re-cont my-repair">
						<p class="message-help " style="margin-top: 10px;">
							带<font style="font-size: 16px; color: red;">*</font>号的为必填项
						</p>
						<dl>													
						<dt>作业名称 :</dt>
							<dd>
								<input  name="name" id="name" placeholder="请在此输入有效课程名称"
									class="repair-text" style="padding: 4px 0" value="${homework.name }"
									type="text" />
							
							</dd>
						</dl>
						
						<dl>	
							<dt>提交期限 :</dt>
							<dd>
								<input name="deadline" readonly="readonly"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-%d'})"
									type="text" placeholder="请点击选择开始时间"
									 id="deadline" value="<fmt:formatDate value="${homework.deadline }"
									pattern="yyyy-MM-dd HH:mm" />" class="repair-text"	
									style="padding: 4px 0; margin-bottom: 10px;" /> 
							</dd>
						</dl>
							<dl>	
							<dt>分数 :</dt>
							<dd>
								<input  name="score" id="score" placeholder="请在此输入有效课程名称"
									class="repair-text" style="padding: 4px 0" value="${homework.score }"
									type="text" />
							</dd>
						</dl>												
						<dl>
							<dt>作业介绍 :</dt>
							<dd>
								<textarea name="introduction"  id ="introduction" placeholder="请在此输入备注信息">${homework.introduction }</textarea>
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

</body>
</html>
<script>
	function applySubmit() {		
		if (true) {
			var parm = $("#apply").serialize();
			postAjaxRequest(basePath()+"/teacher/homework/add",
					parm, function(msg) {
						if (msg.status == true) {
							alert(msg.message);
							goAfterTime(basePath() + "/teacher/homework/"+$("#courseId").val(),1000);							
						} else {
							alert(msg.message);
						}

					});
		}
		}
</script>