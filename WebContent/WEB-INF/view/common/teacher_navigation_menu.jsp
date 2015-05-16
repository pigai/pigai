<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="navbar">
    	<ul class="sf-menu">
          <li><a href="#">首页</a></li>
          <li><a href="#">已开设课程</a> </li>
          <li><a href="#">个人信息</a></li>
          <li><a href="#">修改密码</a> </li>          
     	</ul>
     	
       <div class="u-manfin">
          <span> ${sessionScope.user.userName}&nbsp;老师，欢迎您！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
          <a href="">注销</a>
       </div>
      
    </div> 
   