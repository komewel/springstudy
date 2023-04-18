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
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.js"></script> <!-- lite는 압축버전인거 말고는 다른점이 없다, 부트스트랩 쓸거면 충돌이 좀 있어서 그런지 lite버전이 좋다고 한다. -->	
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.min.js"></script> 
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.css">
<!-- 위같이 리소스파일은 header.jsp를 따로 만들어 한곳에 만들어 두는게 필수이다. -->
<style>
	tbody tr:hover {
		background-color: beige;
		cursor: pointer;
	}
</style>
<script>
	function fnDetail(n) {
		location.href = '${contextPath}/board/detail.do?board_no=' + n;
	}
</script>
</head>
<body>
	<div>
		<a href="${contextPath}/board/write.do">새글작성하기</a> <!-- 앞으로 게시판 매핑은 이런형식으로 연습 -->
	</div>

	<div>
		<table border="1">
			<thead>
				<tr>
					<td>제목</td>
					<td>작성자</td>
					<td>작성일자</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty boardList}">
					<tr>
						<td colspan="3">첫 게시글의 주인공이 되어 보세요!</td>
					</tr>
				</c:if>
				<c:if test="${not empty boardList}">
				<c:forEach items="${boardList}" var="b"> <%-- b는 List<BoardDTO>라고 보면 된다. --%>
					<tr onclick="fnDetail(${b.board_no})"> 
						<td>${b.title}</td> <%-- ${b.getTitle()}처럼 사용하면 안되지만 사용하는법은 알고 있으면 좋다 --%>
						<td>${b.writer}</td>
						<td>${b.created_at}</td>
					</tr>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>

</body>
</html>