<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="info" style="text-align: center"> 
	<table border="0" cellpadding="3" cellspacing="3" style="margin: 0 auto;"> 
        <tr> 
            <td style="text-align: right; padding: 10px"> 
                <label>学号:</label> 
            </td> 
            <td> 
                <input id="studentNo" type="text" size="20" value="${student.studentNo}" /> 
            </td> 
        </tr> 
        <tr> 
            <td style="text-align: right; padding: 10px"> 
                <label>姓名:</label> 
            </td> 
            <td> 
                <input id="name" type="text" size="20" value="${student.name}"/> 
            </td> 
        </tr> 
        <tr> 
            <td style="text-align: right; padding: 10px"> 
                <label>学校:</label> 
            </td> 
            <td> 
                <input id="school" type="text" size="20" value="${student.school}"/> 
            </td> 
        </tr>
        <tr> 
            <td style="text-align: right; padding: 10px"> 
                <label>院系:</label> 
            </td> 
            <td> 
                <input id="college" type="text" size="20" value="${student.college}"/> 
            </td> 
        </tr> 
    </table> 
</div>
</body>
</html>