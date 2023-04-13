<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="${contextPath}/resources/css/init.css">
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	$(function(){
		$('#title').text('Hello World!');
	})
</script> 
</head>
<body>

	<!-- MvcController에서 확인합시다. -->
	<h1 id="title"></h1>
	<img src="${contextPath}/resources/images/animal1.jpg" width="300px"> 
	<!-- src 는 Mapping과 연관된다 resources 들어왔네 내가 처리해줄게 하면서 알아서 찾아준다 -->
	
	<div>
		<a href="${contextPath}/list.do">목록보기</a>
	</div>
	
	<!-- ---------------------------------------------------------------------------------------------------------------------->
	
	<!-- MvcController에서 확인합시다. -->
	<h1>요청 파라미터-1</h1>
	<div><a href="${contextPath}/detail.do">상세보기1</a></div>	
	<div><a href="${contextPath}/detail.do?name=카카오">상세보기2</a></div>	
	<div><a href="${contextPath}/detail.do?age=10">상세보기3</a></div>	
	<div><a href="${contextPath}/detail.do?name=카카오&age=10">상세보기4</a></div>	
	
	<h1>요청 파라미터-2</h1>
	<div><a href="${contextPath}/detail.me">상세보기1</a></div>	
	<div><a href="${contextPath}/detail.me?name=카카오">상세보기2</a></div>	
	<div><a href="${contextPath}/detail.me?age=10">상세보기3</a></div>	
	<div><a href="${contextPath}/detail.me?name=카카오&age=10">상세보기4</a></div>	
	
	<h1>요청 파라미터-3</h1>
	<div><a href="${contextPath}/detail.gdu">상세보기1</a></div>	
	<div><a href="${contextPath}/detail.gdu?name=카카오">상세보기2</a></div>	
	<div><a href="${contextPath}/detail.gdu?age=10">상세보기3</a></div>	
	<div><a href="${contextPath}/detail.gdu?name=카카오&age=10">상세보기4</a></div>	
	
	<!-- ---------------------------------------------------------------------------------------------------------------------->
	
	<!-- DiController에서 확인합시다. -->
	<!-- 이런식으로 mapping하면된다, 테이블로 name을 따로 줘서 관리하면 쉽다. -->
	<h1>Dependency Injection</h1>
	<div><a href="${contextPath}/bbs/detail.do">상세보기</a></div>
	<!-- 매핑이 굳이 짧을 필요는 없다 일부러 길게 하기도 한다 보안문제 때문에 해킹시도가 있을수도 있고, bbs가 내가맡은 테이블이 되는거고 detail.do이 상세보기 하나의 기능역할로 보면된다
		 /bbs/detail.do전체 매핑에서 중간 매핑만 바꾸면서 기능구현, contextPath/mapping인데 contextPath를 변수처리 안하고 수작업으로 작성하면 오류가 발생한다. 주소창에 contextPath가 없는 경우도 변수처리를
		 꼭해야한다(${contextPath}) 그래야지 contextPath없어도 인식한다.
		 이거는 배포하는법 들어가서 배운단다. -->
		 
	 <!-- ---------------------------------------------------------------------------------------------------------------------->
	 <h1>Redirect</h1>
	 <div><a href="${contextPath}/post/detail.do?name=민경태&age=46">상세보기</a></div> 
	 <div><a href="${contextPath}/post/detail.me?name=민경태&age=46">상세보기</a></div> 
	 <!-- 다른 곳으로 넘어갈때는 주소를 변경하여 이동한다. 리다이렉트할때도 값을 가져갈수 있게해주는게
	 		스프링의 특징이다. -->
	 		
	 <!-- 핵심 정리 리다이렉트는 포워드와 달리 매핑값으로!! -->		
	 
	  <!-- ---------------------------------------------------------------------------------------------------------------------->
	 
	 
	 	 
	
</body>
</html>