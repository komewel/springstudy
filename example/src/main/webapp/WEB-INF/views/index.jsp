<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js" ></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>요청 파라미터 1</h1>
	<div><a href="${contextPath}/no1.kyh?name=kyh&no=1">상세보기</a></div>

</body>
</html>