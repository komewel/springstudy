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
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.min.js"></script> 
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.css">
<style>
	tbody tr:hover {
		background-color: beige;
		cursor: pointer;
	}
</style>
<script>
	function fnDetail(n) {
		location.href = '${contextPath}/board/detail.do?boardNo=' + n;
	}
	$(function(){
		let addResult = '${addResult}'  // 언제나 있다고 생각하면 안된다, 전달된 값이 없을수도 있다, el태그는 항상 변수처럼 변하는 값이라  
		if(addResult != '') {			// null을 방지하기 위해 문자열로 인식 시키게 한다. null이뜨면 걍 오류가 뜬다, 값이 전달되지 않았을 상황을 대비해서
										// addResult는 0아니면 1이 뜨기때문에 
			if(addResult == '1') {
				alert('게시글이 등록되었습니다.');
			} else {
				alert('게시글 등록이 실패했습니다.');	
			}
			}
		let removeResult = '${removeResult}';
		if(removeResult != ''){
			if(removeResult == '1'){
				alert('게시글이 삭제되었습니다.');
			} else {
				alert('게시글 삭제가 실패했습니다.');
			}
		}
	})
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
				<c:forEach items="${boardList}" var="b"> 
					<tr onclick="fnDetail(${b.boardNo})"> 
						<td>${b.title}</td> 
						<td>${b.writer}</td>
						<td>${b.createdAt}</td>
					</tr>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>

</body>
</html>