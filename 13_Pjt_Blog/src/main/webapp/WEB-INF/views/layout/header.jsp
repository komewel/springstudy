<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <c:set var="dt" value="<%=System.currentTimeMillis()%>" /> <!-- 계속 시간을 바꾸는 기능으로 캐싱을 해결했다. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${param.title eq null ? 'Welcome' : param.title}</title> <!-- 파라미터로 보낸값은 el태그안에 param.title 처럼 작성해줘야한다. eq null, 파라미터가 안올수도 있다 -->
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.js"></script> 
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.min.js"></script> 
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.css">
<link rel="stylesheet" href="${contextPath}/resources/css/init.css?dt=${dt}" />
<link rel="stylesheet" href="${contextPath}/resources/css/header.css?dt=${dt}" />
</head>
<body>
	<!-- header.do @Getmapping으로 받아서 처리해도 된다. -->
	<div>
		<h1>My Blog</h1>
		<ul>
			<li><a href="${contextPath}/blog/list.do">블로그</a></li>
			<li><a href="${contextPath}/qna/list.do">QnA</a></li>
		</ul>
	</div>
	
	
	<hr>


































	

</body>
</html>