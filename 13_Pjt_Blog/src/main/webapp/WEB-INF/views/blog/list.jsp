<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<jsp:include page="../layout/header.jsp">
	<jsp:param value="블로그" name="title" />
</jsp:include>

	<div>
	
		<h1>블로그 목록</h1>
	
	
	<c:if test="${sessionScope.loginId ne null}">
		<div>
			<input type="button" value="블로그 작성하기" onclick="fnWrite()">
		</div>
	</c:if>
	
	<div>
	 <table border="1">
	  <caption style="text-align: center;">${pagination}</caption>
	 	<thead>
 		 <tr>
 		  <td>번호</td>
 		  <td>제목</td>
 		  <td>조회수</td>
 		  <td>작성자</td>
 		  <td>작성일</td>
 		 </tr>
	 	</thead>
	 	<tbody>
	 	 <c:forEach items="${blogList}" var="blog" varStatus="vs">
	 	  <tr>
	 	   <td>${beginNo - vs.index}</td>
	 	   <td>
	 	   <!-- 내가 작성한 블로그는 조회수가 증가하지 않는다. -->
	 	    <c:if test="${sessionScope.loginId eq blog.memberDTO.id}">
	 	     <a href="${contextPath}/blog/detail.do?blogNo=${blog.blogNo}">${blog.title}</a> <!-- 작성자랑 조회하는 사람이 같으면 바로 detail.do로 넘어간다 -->
	 	    </c:if>
	 	    <c:if test="${sessionScope.loginId ne blog.memberDTO.id}">
	 	     <a href="${contextPath}/blog/increaseHit.do?blogNo=${blog.blogNo}">${blog.title}</a> <!-- 작성자랑 조회하는 사람이 같으면 바로 detail.do로 넘어간다 -->
	 	    </c:if>
	 	   </td>
	 	   <td>${blog.hit}</td>
	 	   <td>${blog.memberDTO.id}</td>
	 	   <td>${blog.createdAt}</td>
	 	  </tr>
	 	 </c:forEach>
	 	</tbody>
	 </table>
	</div>	
	
	</div>

<script>
	function fnWrite() {
		location.href = '${contextPath}/blog/write.form';
	}
</script>	

</body>
</html>