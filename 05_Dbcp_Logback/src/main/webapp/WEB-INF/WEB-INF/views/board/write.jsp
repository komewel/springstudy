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
<script>
	
	function fnList() {
		location.href = "${contextPath}/board/list.do";		
	}

	$(function(){  /* summernote 들어가서 deepdive 들어가면 예시 있음, 볼드체나 기울임은 db에 저장될 때 태그로 저장된다. */
					/* db에는 태그로 정리되어서 저장된다, 내용은 CLOB으로 저장하는게 좋다 태그로 저장되어서 얼마나 저장될지 몰라서 */
		$('#content').summernote({
			width: 640,
			height: 480,
			lang: 'ko-KR',
			toolbar: [
				  ['style', ['bold', 'italic', 'underline', 'clear']],
				  ['font', ['strikethrough', 'superscript', 'subscript']],
				  ['para', ['ul', 'ol', 'paragraph']],
				  ['table', ['table']],
				  ['fontname', ['fontname']], 
				  ['color', ['color']],
				  ['insert', ['link', 'picture', 'video']],
				  ['view', ['fullscreen', 'codeview', 'help']]
				],
		})
	})
	
	
</script>

</head>
<body>

	<div>
		<h1>작성화면</h1>
		<form method="post" action="${contextPath}/board/add.do">
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title">
			</div>
			<div>
				<label for="writer">작성자</label>
				<input type="text" id="writer" name="writer">
			</div>	
			<div>
				<label for="content">내용</label>
				<textarea id="content" name="content"></textarea> <!-- summernote 편집기로 바뀌는 textarea -->
			</div>	
			<div>
				<button>작성완료</button>
				<input type="button" value="목록" onclick="fnList()">
			</div>		
		</form>
	</div>

</body>
</html>