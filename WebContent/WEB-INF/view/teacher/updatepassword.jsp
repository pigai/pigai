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
<title>密码修改</title>
</head>
<body>
	
<div id="body_container"> 
	<div class="w1000">

		<jsp:include page="../common/teacher_navigation_menu.jsp" />
		
		<div class="cont">
			<div class="current-position">
				<span class="font14"> 当前位置：<a href="${pageContext.request.contextPath }/">首页</a> &gt;&gt; 
					<a href="">修改密码</a></span>
					
			</div>

			<div class="repair_con mt10">
				<div class="repair_title">
					<ul>
					    <li  class="current cur" >修改密码</li>															
					</ul>		
					<span class="back">
						<a href="${from_url eq null ? pageContext.request.contextPath : from_url }">&lt;&lt;返回</a></span>
				</div>
            <div class="repair_main">
	            	<div id="updatepass" style="text-align: center"> 
						<table border="0" cellpadding="8" cellspacing="8" style="margin: 0 auto;margin-top:100px;""> 
					        <tr> 
					            <td style="text-align: right; padding: 10px"> 
					                <label>旧密码:</label> 
					            </td> 
					            <td> 
					                <input id="oldPassword" type="text" size="20" style="width:150px;height:30px;border:1px solid #A6D9EB;"/> 
					            </td> 
					        </tr> 
					        <tr> 
					            <td style="text-align: right; padding: 10px"> 
					                <label>新密码:</label> 
					            </td> 
					            <td> 
					                <input id="newPassword" type="text" size="20" style="width:150px;height:30px;border:1px solid #A6D9EB;"/> 
					            </td> 
					        </tr> 
					        <tr> 
					            <td style="text-align: right; padding: 10px"> 
					                <label>新密码:</label> 
					            </td> 
					            <td> 
					                <input id="newPasswordAgain" type="text" size="20" style="width:150px;height:30px;border:1px solid #A6D9EB;"/> 
					            </td> 
					        </tr>
					        <tr align="right"> 
					            <td colspan="2"> 
					               <input type="submit" id="Updatepass" value="&nbsp;&nbsp;确&nbsp;认&nbsp;&nbsp;" style="margin:10px 50px 0 0;width:50px;height:25px;background:#DEF2F9;border:1px solid #A6D9EB ">&nbsp;         
					            </td> 
					        </tr> 
					    </table> 
				</div>	
		</div>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp" />
</div>
</div>
<script type="text/javascript">
$().ready(function () {
	$('#Updatepass').click(function () {
		var oldpassword = $('#oldpassword').val();
		var newpassword = $('#newpassword').val();
		var newpasswordagain = $('#newpasswordagain').val();
		if (oldpassword == "") {			
			alert("旧密码不能为空！");
		}else if(newpassword == ""){
			alert("新密码不能为空！");
		}else if(newpasswordagain == ""){
			alert("请再次输入新密码！");
		}else if(newpassword != newpasswordagain){
			alert("两次密码输入不一致！");
		}
		else {
			var data = {"oldpassword":oldpassword,"newpassword":newpassword};
			$.ajax({
				type: "post",
				url: "updatepassword",
				data: data,
				success: function (msg) {
					if (msg.status == true) {
						location.href = "updatepassword"; //如果登录成功则跳到管理界面
						alert(msg.message);
					}
					if (msg.status == false) {
						alert(msg.message);
					}
				},
		});
	}
});
});
</script>
</body>
</html>