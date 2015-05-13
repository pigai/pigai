<%
request.setAttribute("CURRENTUSER", request.getSession().getAttribute("user"));
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/init.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>批改网欢迎您</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/superfish.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/skin/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/plugin/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.artDialog.source.js?skin=blue"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iframeTools.source.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/plugin/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
	function showStu(){
		$("#stuEn").removeClass("titleLeft");
		$("#stuEn").addClass("titleCurrent");
		$("#teaEn").removeClass("titleCurrent");
		$("#teaEn").addClass("titleLeft");		
		$("#studentEnter").removeClass("hide");
		$("#studentEnter").addClass("show");
		$("#teacherEnter").removeClass("show");
		$("#teacherEnter").addClass("hide");
	}	
	function showTea(){
		$("#teaEn").removeClass("titleLeft");
		$("#teaEn").addClass("titleCurrent");
		$("#stuEn").removeClass("titleCurrent");
		$("#stuEn").addClass("titleLeft");	
		$("#teacherEnter").removeClass("hide");
		$("#teacherEnter").addClass("show");
		$("#studentEnter").removeClass("show");
		$("#studentEnter").addClass("hide");
	}
	function register(){
		goWithUrl(basePath()+"/student/register");
	}
	$().ready(function () {
		$('#stulogin').click(function () {
			var studentNo = $('#studentNo').val();
			var password = $('#stuPassword').val();
			if (studentNo == "" || password == "") {			
				alert("学名或密码不能为空！");
			}
			else {
				var data = {"studentNo":studentNo,"password":password};
				$.ajax({
					type: "post",
					url: "../student/login",
					data: data,
					success: function (msg) {
						if (msg.status == true) {
							location.href = "../student/course"; //如果登录成功则跳到管理界面
							alert(msg.message);
						}
						if (msg.status == false) {
							alert(msg.message);
						}
					},
			});
		}
	});
		$('#tealogin').click(function () {
			var studentNo = $('#teacherNo').val();
			var password = $('#teaPassword').val();
			if (studentNo == "" || password == "") {			
				alert("工号或密码不能为空！");
			}
			else {
				var data = {"teacherNo":studentNo,"password":password};
				$.ajax({
					type: "post",
					url: "../teacher/login",
					data: data,
					success: function (msg) {
						if (msg.status == true) {
							location.href = "../course"; //如果登录成功则跳到管理界面
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
</head>
<body>
<div id="outerwrap">

	<div id="home_nav"><img src="${pageContext.request.contextPath }/images/title.png"/></div>

    <div id="contentBox">
	<div id="welcome"><img src="${pageContext.request.contextPath }/images/4个人.png"/></div>

	<div id="logPanel">
	
		<div id="stuEn" class="titleCurrent" onclick="showStu()"><span>学生入口</span></div>
		<div id="teaEn" class="titleLeft" onclick="showTea()"><span>教师入口</span></div>
		<div class="clear"></div>		
		<div id="studentEnter" class="show">
		<label>学号:&nbsp;</label><input id="studentNo" type="text" size="20" ></input> <br />
		<label>密码:&nbsp;</label><input id="stuPassword" type="password" size="20" ></input> <br />
		<input type="button"  id="stulogin" value="&nbsp;&nbsp;登&nbsp;录&nbsp;&nbsp;" ></input>&nbsp;&nbsp;
		<input type="button"  id="sturegister" value="&nbsp;&nbsp;注&nbsp;册&nbsp;&nbsp;" onclick = "register()" ></input>
		</div>		
		<div id="teacherEnter" class="hide">		
		<label>工号:&nbsp;</label><input id="teacherNo" type="text" size="20" > </input><br />
		<label>密码:&nbsp;</label><input id="teaPassword" type="password" size="20" ></input> <br />
		<input type="button"  id="tealogin" value="&nbsp;&nbsp;登&nbsp;录&nbsp;&nbsp;" ></input>&nbsp;&nbsp;
		<!-- <input type="button"  id="tearegister" value="&nbsp;&nbsp;注&nbsp;册&nbsp;&nbsp;" ></input>	 -->
		</div>
	</div>	
	<div class="clear"></div>
	</div>
</div>	

</body>
</html>
