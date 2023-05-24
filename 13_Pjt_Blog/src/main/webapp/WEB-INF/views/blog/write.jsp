<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<jsp:include page="../layout/header.jsp">
	<jsp:param value="블로그작성" name="title" />
</jsp:include>
<script>

	// 자바스크립트 파일(*.js)에서는 ${contextPath}가 인식되지 않기 때문에 
	// ContextPath를 반환하는 별도의 자바스크립트 코드가 필요하다.
	function getContextPath() {
		let begin = location.href.indexOf(location.origin) + location.origin.length;
		let end   = location.href.indexOf("/", begin + 1);
		return location.href.substring(begin, end);
	}

	function fnList() {
		location.href = getContextPath() + '/blog/list.do';       // jsp파일에서는 java코드를 쓸수 있지만 js에서는 못쓰므로 따로 별도 파일로 구성하면 에러가 난다.
	}
	
	$(function(){
		$('#content').summernote({
			width: 1280,
			height: 1024,
			lang: 'ko-KR',
			toolbar: [
				['style', ['bold', 'italic', 'underline', 'clear']],
				['font', ['strikethrough', 'superscript', 'subscript']],
				['fontname', ['fontname']],
				['color', ['color']],
				['para', ['ul', 'ol', 'paragraph']],
				['table', ['table']],
				['insert', ['link', 'picture', 'video']],
				['view', ['fullscreen', 'codeview', 'help']]
			], 
			callbacks: { /* 편집기로 이미지 업로드시 실행되는 function 직접 db로 저장되는게 아니라 경로만 하드디스크에 저장되고 경로만 db에 저장되는 기능을 제공한다  */
				onImageUpload: function(files) { 			  // summernote 편집기에 이미지를 올리면 해당 이미지의 정보는 function의 매개변수 files(file이 하나가 아니라 여러개도 가능하다(배열))로 전달된다.
					for(let i = 0; i < files.length; i++) {   // files 배열을 순회하면서 이미지를 *하나씩* 처리한다.
					 	var formData = new FormData();		  // ajax로 파일첨부하기(전에 배운 mvc패턴말고), 가상 form(주로 ajax 처리에서 사용)
					 	formData.append('file', files[i]);	  // 가상 formData에 추가한 요소
					 	$.ajax({                              // 가상 form에 저장된 이미지를 HDD에 저장하고 저장된 경로(매핑)와 이름을 받아오는 ajax
					 		contentType: false,
					 		processData: false,				  // contentType, processData: ajax를 이용한 파일 첨부에서 사용
					 		type: 'post',
					 		url: getContextPath() + '/blog/imageUpload.do',
					 		data: formData,
					 		dataType: 'json',
					 		success: function(resData) { 	  // resData = {"src": "/app13/imageLoad/abcdefg.jpg"}
					 		// 이미지가 첨부되면서 실제로 동작한다.	
					 			$('#content').summernote('insertImage', resData.src); // content에 <img src="/app13/imageLoad/abcdefg.jpg"> 태그가 만들어지고 src 속성이 만들어진다.
					 																  // content에 추가된 img 태그의 src 속성 값은 servlet-context.xml의 resources 태그에 의해서 실제로는 /storage/summernote/abcdefg.jpg로 처리된다
					 																  // 서버에 저장된게 아니라 하드에 저장된다. 서버에는 경로만
					 		}
					 	})	// ajax
					}  // for
				}	// onImageUpload
			}	// callbacks
		})
	})
	
</script>
</head>
<body>
	<!-- 로그인을 한 사람만 작성할수있게 했는데 로그인 유무를 확인할수 있는 방법은 하드한코딩도 방법이지만 12강에 interceptor를 이용해서 좀 더 세밀하게 작업 할수도 있다.(servlet-context를 확인하자) -->
	<div>
		<h1>작성화면</h1>
		<form method="post" action="${contextPath}/blog/add.do">
			<div>
				작성자 : ${sessionScope.loginId}
			</div>
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title">
			</div>
			<div>
				<textarea id="content" name="content"></textarea>  <!-- summernote 편집기로 바뀌는 textarea -->
			</div>
			<div>
				<input type="hidden" name="memberNo" value="${sessionScope.loginNo}"> 
				<button>작성완료</button>
				<input type="button" value="목록" onclick="fnList()">
			</div>
		</form>
	</div>
	
</body>
</html>