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
<title>�γ̽���</title>
<%@include file="../common/head.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/course.js"></script>
</head>
<body>
	

	<div class="w1000">

		<jsp:include page="../common/navigation_menu.jsp" />


		<div class="cont">
			<div class="current-position">
				<span class="font14"> ��ǰλ�ã�<a href="${pageContext.request.contextPath }/">��ҳ</a> &gt;&gt; 
					<a href="${pageContext.request.contextPath }/course"> �γ�</a> &gt;&gt; ��ҵ���� </span>
					
			</div>

			<div class="repair_con mt10">
				<div class="repair_title">
					<ul>
					    <li  class="current cur" onclick="go('${pageContext.request.contextPath }/homework/detail/${homeworkId }')">��ҵ����</li>												
						<li  onclick="go('${pageContext.request.contextPath }/homework/submit/${homeworkId }')">��ҵ����</li>											
					</ul>		
					<span class="back">
						<a href="${from_url eq null ? pageContext.request.contextPath : from_url }">&lt;&lt;����</a></span>
				</div>
            <div class="repair_main">
            				
					<div class=" Re-cont my-repair">		
						<dl>	
							<dt>��ҵ���� :</dt>
							<dd>
							${homework.name }
							</dd>
						</dl>												
						<dl>
							<dt>��ҵ���� :</dt>
							<dd>
								${homework.introduction }
							</dd>
						</dl>
						
					   <dl>
							<dt>����</dt>
							<dd>
								${homework.score}
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