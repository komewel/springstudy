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
		// 제목을 클릭하면 정렬 방식을 바꿈
		$('.title').on('click', function(){
			location.href = '${contextPath}/employees/pagination.do?column=' + $(this).data('column') + '&order=' + $(this).data('order') + "&page=" + ${page}; // 현재정렬이 무엇인지 알고있어야 제대로 된 값을 넘겨줄수 있다. 그래서 따로 값을 넘겨줄라고 ? 를 붙였다, 현재 페이지가 어디인지 정렬방식(해야될일, 이값은 누군가 알고 있어야 한다.)이 뭔지 전달이 필요하다.
		}) // data속성값을빼내오는과정 이건 제이쿼리 방법인데 this는 제이쿼리 객체가 아니라서 제이쿼리화 시켰다, 현재 보고있는 page값은 jsp에 정보가 없다 근데 service는 기억하고 있으므로 서비스에서 model에 저장해준걸 쓰면된다. 
	})
	
</script>
<style>
	.title {
		cursor: pointer;
	}
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
		color: orange;
	}
	table {
		width: 1500px;
	}
	table td:nth-of-type(1) { width: 100px;}
	table td:nth-of-type(2) { width: 150px;}
	table td:nth-of-type(3) { width: 300px;}
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
					<td><span class="title" data-column="E.EMPLOYEE_ID" data-order="${order}">사원번호</span></td> <%-- 태그에 데이터를 저장하는 방법 data- 까지는 형식 나머지는 자기맘대로 --%>
					<td><span class="title" data-column="E.FIRST_NAME" data-order="${order}">사원명</span></td>
					<td><span class="title" data-column="E.EMAIL" data-order="${order}">이메일</span></td>
					<td><span class="title" data-column="E.PHONE_NUMBER" data-order="${order}">전화번호</span></td>
					<td><span class="title" data-column="E.HIRE_DATE" data-order="${order}">입사일자</span></td>
					<td><span class="title" data-column="E.JOB_ID" data-order="${order}">직업</span></td>
					<td><span class="title" data-column="E.SALARY" data-order="${order}">연봉</span></td>
					<td><span class="title" data-column="E.COMMISSION_PCT" data-order="${order}">커미션</span></td>
					<td><span class="title" data-column="E.MANAGER_ID" data-order="${order}">매니저</span></td>
					<td><span class="title" data-column="E.DEPARTMENT_ID" data-order="${order}">부서번호</span></td>
					<td><span class="title" data-column="D.DEPARTMENT_NAME" data-order="${order}">부서명</span></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${employees}" var="emp" varStatus="vs"> <!-- 인덱스를 쓰려면 varStatus를 선언해줘야 함 -->
					<tr>
						<td>${beginNo - vs.index}</td>
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