<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生注册</title>
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
</head>
<body>
<div id="outerwrap">

	<div id="home_nav"><img src="${pageContext.request.contextPath }/images/title.png"/></div>

    <div id="contentBox">
	<div id="welcome"><img src="${pageContext.request.contextPath }/images/4个人.png"/></div>

	<div id="studentlogPanel">
		<div id="panelTitle"><span>学生注册</span></div>
			
		<label>学号:</label><input id="studentNo" type="text" size="20" /><br/> 
		<label>姓名:</label><input id="name" type="text" size="20" /><br/> 
		<label>学校:</label><input id="school" type="text" size="20" /><br/> 
		<label>院系:</label><input id="college" type="text" size="20" /><br/> 
		<label>密码:</label><input id="password" type="text" size="20" /><br/> 
		<label>确认密码:</label><input id="password_again" type="text" size="20" style="margin-right:38px" /><br/> 		
		
		<input type="submit" id="register" value="&nbsp;&nbsp;注&nbsp;册&nbsp;&nbsp;" style="width:100px">
	</div>	
	<div class="clear"></div>
	</div>
</div>	

<script type="text/javascript" src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
$().ready(function () {
	$('#register').click(function () {
		var studentNo = $('#studentNo').val();
		var name = $('#name').val();
		var school = $('#school').val();
		var college = $('#college').val();
		var password = $('#password').val();
		var password_again = $('#password_again').val();
		if (studentNo == "") {			
			alert("学号不能为空！");
		}else if(name == ""){
			alert("姓名不能为空！");
		}else if(school == ""){
			alert("学校不能为空！");
		}else if(college == ""){
			alert("院系不能为空！");
		}else if(password == ""){
			alert("密码不能为空！");
		}else if(password_again == ""){
			alert("请再次输入密码！");
		}else if(password != password_again){
			alert("两次密码不符");
		}
		else {
			var data = {"studentNo":studentNo,"name":name,"school":school,"college":college,"password":password};
			$.ajax({
				type: "post",
				url: "register",
				data: data,
				success: function (msg) {
					if (msg.status == true) {
						alert(msg.message);
						location.href = "../home/homepage"; 
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