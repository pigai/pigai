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
</head>
<body >
<div id="body_container">	
	
		
		<div class="cont">
		

			<div class="repair_con mt10">
		
            <div class="repair_main">
            
				<form id="apply" action="${pageContext.request.contextPath}/teacher/homework/grade"	method="post" enctype="multipart/form-data" >
					
					<input type="hidden" name="submitId" id="submitId"  value="${id }" />					
					
					<div class=" Re-cont my-repair">
						<p class="message-help " style="margin-top: 10px;">
							带<font style="font-size: 16px; color: red;">*</font>号的为必填项
						</p>											
						<dl>	
							<dt>*分数 :</dt>
							<dd>
							<input name="score" id="score" placeholder="请在此输入有效的整数"
									class="repair-text" style="padding: 4px 0"
									type="text" />
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

</div>
<script>
	function applySubmit() {
		if(!stringNotNull($("#score").val())){
			alert("请输入分数");
			return false;
		}			
		else {
			var parm = $("#apply").serialize();				
			postAjaxRequest(basePath()+"/teacher/homework/grade/"+$("#submitId").val(),
					parm, function(msg) {
						if (msg.status == true) {
							alert(msg.message);
							window.top.location.reload();
						} else {
							alert(msg.message);
						}

					});		
		}
	}
</script>
</body>
</html>
