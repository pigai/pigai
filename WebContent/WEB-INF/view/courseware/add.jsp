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
<title>添加课程</title>
<%@include file="../common/head.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/course.js"></script>
</head>
<body>
	

	<div class="w1000">

		<jsp:include page="../common/navigation_menu.jsp" />


		<div class="cont">
			<div class="current-position">
				<span class="font14"> 当前位置：<a href="${pageContext.request.contextPath }/">首页</a> &gt;&gt; 
					<a href="${pageContext.request.contextPath }/course"> 课程</a> &gt;&gt; 课程添加 </span>
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
            
				<form id="apply" action="${pageContext.request.contextPath}/courseware/add"	method="post" enctype="multipart/form-data" >
					
					<input type="hidden" name="courseId" id="courseId"  value="${courseId }" />					
					
					<div class=" Re-cont my-repair">
						<p class="message-help " style="margin-top: 10px;">
							带<font style="font-size: 16px; color: red;">*</font>号的为必填项
						</p>											
						<dl>	
							<dt>课件名称 :</dt>
							<dd>
							<input name="coursewareName" id="coursewareName" placeholder="请在此输入有效课程名称"
									class="repair-text" style="padding: 4px 0"
									type="text" />
							</dd>
						</dl>									
								<dl>	
							<dt>*选择文件 :</dt>
							<dd>
							<input type="file" id="file" name="file"/> 
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
	<jsp:include page="../common/footer.jsp" />
</div>

</body>
</html>
<script>
	function applySubmit() {
		if(!stringNotNull($("#file").val())){
			alert("请选择文件");
			return false;
		}
		
		if(!stringNotNull($("#coursewareName").val())){
			alert("请输入课件名称");
			return false;
		}		
		else {
			var parm = $("#apply").serialize();
			//var parm = {"coursewareName":$("#coursewareName").val(),"courseId":$("#courseId").val()};
			  $.ajaxFileUpload
	            (
	                {
	                    url: basePath()+"/courseware/add", //用于文件上传的服务器端请求地址
	                    secureuri: false, //是否需要安全协议，一般设置为false
	                    fileElementId: 'file', //文件上传域的ID
	                    data:parm,
	                    dataType: 'json', //返回值类型 一般设置为json
	                    success:function (msg) {
	                   	 if (msg.status == true) {
								alert(msg.message);
								doAfterTime(function() {
									window.top.location.reload();
								}, 1000);						
							} else {
								alert(msg.message);
							}
	                 },
	                    error: function (data, status, e)//服务器响应失败处理函数
	                    {
	                        alert(e);
	                    }
	                }
	            );
			
			/*  $.ajax({
                 type: "POST",
                 dataType: "multipart/form-data",
                 url: basePath()+"/courseware/add",
                 data:parm,
                 success: function (msg) {
                	 if (msg.status == true) {
							alert(msg.message);
							doAfterTime(function() {
								window.top.location.reload();
							}, 1000);						
						} else {
							alert(msg.message);
						}
                 },
                 error: function(data) {
                     alert("error:"+data.message);
                  }
             });		 */
		}
		}
</script>