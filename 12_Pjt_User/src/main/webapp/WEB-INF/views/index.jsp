<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
  function fnLeave(){
    if(confirm('동일한 아이디로 재가입이 불가능합니다. 회원 탈퇴하시겠습니까?')){
      location.href = '${contextPath}/user/leave.do';
    }
  }
</script>
</head>
<body>

	<div>
		<!-- 
		.form : 화면으로 
		.do : 실제 수행으로			
		-->
		<!-- 로그인이 안 된 상태 pagecontext, request, session(${sessionScope.loginId}로 불러줄수 있음) application el로 다 불러들일수 있는 저장기억소 -->
	    <c:if test="${sessionScope.loginId == null}">    
	      <a href="${contextPath}/user/agree.form">회원가입</a>
	      <a href="${contextPath}/user/login.form">로그인</a>
	    </c:if>
	    
	    <!-- 로그인이 된 상태 -->
	    <c:if test="${sessionScope.loginId != null}">
	      <div>
	        <a href="#">${sessionScope.loginId}</a>님 반갑습니다 ♥
	      </div>
			<div>
				<a href="${contextPath}/user/logout.do">로그아웃</a>
       			<a href="javascript:fnLeave()">회원탈퇴</a> <!-- a링크에서 쓸수있는 자바스크립트기능 -->
			</div>
		</c:if>
		
		<!-- 관리자 접속, if문으로 해도된다-->
		<!-- <c:if test="${sessionScope.loginId == admin}"></c:if> -->
	
  </div>
  
</body>
</html>