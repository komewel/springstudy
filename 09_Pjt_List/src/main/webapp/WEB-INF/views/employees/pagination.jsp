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
	$(function(){
		// recordPerPage의 변경
		$('#recordPerPage').on('change', function(){
			location.href = '${contextPath}/employees/change/record.do?recordPerPage=' + $(this).val();	// session에 recordPerPage올리기 	
		})
		<%-- 세션에 저장된 recordPerPage값으로 <select> 태그의 값을 세팅 --%>
		<%-- 속성저장하는 애들은 el태그로 호출하면 된다. ${저장된객체.호칭}으로 부르면 된다.--%>
		let recordPerPage = '${sessionScope.recordPerPage}' == '' ? '10' : '${sessionScope.recordPerPage}';
		$('#recordPerPage').val(recordPerPage); 
	})
</script>
<style>
	.pagination {
		width: 355px;
		margin: 0 auto;
	}
	
	.pagination span, .pagination a  {
		display: inline-block;
		width: 50px;
	}
	.hidden {
		visibility: hidden;
	}
	.strong {
		font-weight: 900;
	}
	.link {
		
	}
</style>
</head>
<body>
	<!-- 페이지 넘버링 하는걸 pagination이라고 한다, row = record, field = column 
	recordPerPage, 한화면에 몇개씩 보여줄까 
	pagePerBlock, 한페이지 목록개수 -->
	<div>
	<a href="${contextPath}/employees/search.form">사원 조회 화면으로 이동</a>
	</div>
	
	<div> 
		<h1>사원목록</h1>
		<div>
	<!-- change란 이벤트가 필요하다 페이지를 개수에따라 다르게 구성해야하기 때문에  -->
			<select id="recordPerPage">
				<option value="10">10개</option>
				<option value="20">20개</option>
				<option value="30">30개</option>
			</select>
		</div>
		<hr>
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>
					<td>사원번호</td>
					<td>사원명</td>
					<td>이메일</td>
					<td>전화번호</td>
					<td>입사일자</td>
					<td>직업</td>
					<td>연봉</td>
					<td>커미션</td>
					<td>매니저</td>
					<td>부서번호</td>
					<td>부서명</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${employees}" var="emp" varStatus="vs"> <!-- 인덱스를 쓰려면 varStatus를 선언해줘야 함 -->
					<tr>
						<td>${vs.index}</td>
						<td>${emp.employeeId}</td>
						<td>${emp.firstName} ${emp.lastName}</td>
						<td>${emp.email}</td>
						<td>${emp.phoneNumber}</td>
						<td>${emp.hireDate}</td>
						<td>${emp.jobId}</td>
						<td>${emp.salary}</td>
						<td>${emp.commissionPct}</td>
						<td>${emp.managerId}</td>
						<td>${emp.deptDTO.departmentId}</td>
						<td>${emp.deptDTO.departmentName}</td> <!-- 부서명이 없는데 어떻게 가져오냐..?, 바로 FK로 테이블끼리 연관 시키는게 핵심 -->
					</tr>			
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="12">
						${pagination}
					</td>
				</tr>
			</tfoot>
		</table>
	</div>

</body>
</html>