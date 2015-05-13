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
		
		<div class="cont">
		

			<div class="repair_con mt10">
		
            <div class="repair_main">
            
				<form id="apply" action="${pageContext.request.contextPath}/student/homework/submit"method="post" enctype="multipart/form-data" >
					
					<input type="hidden" name="courseId" id="courseId"  value="${courseId }" />					
					
					<div class=" Re-cont my-repair">
						<p class="message-help " style="margin-top: 10px;">
							带<font style="font-size: 16px; color: red;">*</font>号的为必填项
						</p>																				
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
	<jsp:include page="../../common/footer.jsp" />
</div>

</body>
</html>
<script>
	function applySubmit() {
		if(!stringNotNull($("#file").val())){
			alert("请选择文件");
			return false;
		}else {
			var parm = $("#apply").serialize();		
			//var parm = {"coursewareName":$("#coursewareName").val(),"courseId":$("#courseId").val()};
			  $.ajaxFileUpload
	            (
	                {
	                    url: basePath()+"/student/homework/submit?"+parm, //用于文件上传的服务器端请求地址
	                    secureuri: false, //是否需要安全协议，一般设置为false
	                    fileElementId: 'file', //文件上传域的ID	                    
	                    dataType:'json',
	                    success:function (msg) {
	                    	
	                    	if (msg.status == true) {
	                    		alert(msg.message);
	                    		doAfterTime(function() {
									window.top.location.reload();
								}, 1000);
	    					}
	    					if (msg.status == false) {
	    						alert(msg.message);
	    					}	                    	                    
				},
				error : function(msg) {
					alert(msg.message);
				}
			});

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