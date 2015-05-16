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
<title>课程介绍</title>
<%@include file="../../common/head.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/homework.js"></script>
</head>
<body>
<div id="body_container">		
	<div class="w1000">

		<jsp:include page="../../common/student_navigation_menu.jsp" />


		<div class="cont">
			<div class="current-position">
				<span class="font14"> 当前位置：<a href="${pageContext.request.contextPath }/">首页</a> &gt;&gt; 
					<a href="${pageContext.request.contextPath }/student/course"> 课程</a> &gt;&gt; 课件 </span>
					
			</div>

			<div class="repair_con mt10">
				<div class="repair_title">
					<ul>
					    <li onclick="go('${pageContext.request.contextPath }/student/course/detail/${courseId }')">课程详情</li>						
						<li onclick="go('${pageContext.request.contextPath }/student/courseware/${courseId }')">课件</li>
						<li class="current cur" onclick="go('${pageContext.request.contextPath }/student/homework/${courseId }')">作业</li>		
					</ul>
					<span class="back">
						<a href="${from_url eq null ? pageContext.request.contextPath : from_url }">&lt;&lt;返回</a></span>
				</div>
              
          <div class="repair_main">      
          
            <div class="repair_search">                          
                </div>   
                    <div class="att_table">
            	 <table width="100%" align="center" class="tb_list" border="0" cellspacing="0" cellpadding="0">
                 	<tbody>
                    	<tr>                    
                           <td class="f_1" width="15%">作业名称</td>
                           <td class="f_1" width="15%">作业分数</td>
                           <td class="f_1" width="15%">截止时间</td>
                           <td class="f_1" width="15%">创建时间</td>
                           <td class="f_1" colspan="3" width="15%">操作</td>
                        </tr>
                        
                      <c:if test="${empty pageModel.pageData }">
              	 <tr class="select" >
              	 	<td colspan="8"><li style="color: gray;text-align: center;">没有相关信息</li></td>
              	 </tr>
              </c:if>
              <c:forEach items="${pageModel.pageData }" var="homework" varStatus="status">
               <tr<c:if test="${status.index % 2 == 1}"> class="row"</c:if>> 
               <td class="f_2">${homework.name }</td>     
               <td class="f_2">${homework.score }</td>        
                 <td class="f_2"><fmt:formatDate value="${homework.deadline }"
											pattern="yyyy-MM-dd HH:mm" /></td>
               <td class="f_2"><fmt:formatDate value="${homework.createTime }"
											pattern="yyyy-MM-dd HH:mm" /></td>
                                  	                                                   	                    								                  
                    	<td class="f_2">                             
                            	<a href="javascript:void(0);" onclick="goWithUrl('${pageContext.request.contextPath }/student/homework/detail/${homework.homeworkId}');">查看</a>                                                        	                                                                                   
                            	                            
                            	                        	                            	
                            </td>                            
                        </tr>   
             </c:forEach>
                                             
                    </tbody>
                 </table>
            </div>
                </div>
          
            <div class="pager-area" >
        	<pg:pager url="${pageContext.request.contextPath}/homework/${courseId }" items="${pageModel.totalRecord}"
					maxIndexPages="10" export="currentPageNumber=pageNumber" maxPageItems="${pageModel.pageSize}">												
					<pg:index>
						<pg:first><a class="next-page" href="${pageUrl}&amp;currentIndex=${pageNumber}">首页</a></pg:first> 
						<pg:prev><a  class="prev-page" href="${pageUrl }&amp;currentIndex=${pageNumber}">前页</a></pg:prev> 
						<pg:pages>
						<c:choose>
						<c:when test="${pageNumber eq currentPageNumber }">
						<span class="page-num current-page">${pageNumber }</span>						   							
							</c:when>
							<c:otherwise> 
							<a class="page-num" href="${pageUrl }&amp;currentIndex=${pageNumber }">${pageNumber}</a> 
						</c:otherwise> 
					</c:choose> 
					</pg:pages> 
							<pg:next><a class="next-page" href="${pageUrl }&amp;currentIndex=${pageNumber }">下页</a></pg:next> 
							<pg:last><a class="next-page" href="${pageUrl }&amp;currentIndex=${pageNumber }">尾页</a>		
							</pg:last>
					</pg:index>
			</pg:pager>
         </div>
                   
     </div>
    </div>
           
        <jsp:include page="../../common/footer.jsp" ></jsp:include>
    </div>
 </div>
</body>
</html>
