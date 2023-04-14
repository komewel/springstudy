<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
</head>
<body>
<script>
	function fn1() {
		$.ajax({
			// 요청
			type: 'post', // 서버로 보낼 데이터를 요청 본문(@request body)에 저장해서 보낸다, 주소창에서 확인이 안된다.
			url: '${contextPath}/third/ajax1',
			data: JSON.stringify({ // json내장객체중 json문서를 bean이나 map형태로 바꿔준다, 객체 -> JSON(문자열) 변환을 해준다, json 데이터 자체를 서버로 보낼라고, 어디서나 자유롭게 쓸수있는 메소드(자바스크립트) 
				'name': $('#name').val(),  // 사용자가 입력한 데이터, 문자열 형식의 JSON데이터를 서버로 보내야하는데, 어라라... 파라미터 이름이 없다, 다짜고짜 데이터만 보낸다, 이렇게 보내면 진짜 문자열로만 보내질텐데...
				'tel': $('#tel').val()	// 파리미터 이름이 없기떄문에 서버에서 파라미터로 받을수가없다. 원래는 name= 이런식으로 해야하는데 그래서 이런식으로 다짜고짜 데이터만 받으면 정상적으로 수행할수 있는건 객체(Contact)랑 map뿐이다(이거조차도 jackson이해준다)
			}),							// 결론은 그래서 get방식이 안된다 주소창으로 파라미터를 보내봤자 정상처리도 안될텐데... 뭐하러 get방식을 고수해 post로 하자 그래서 post구나			
			contentType: 'application/json',  // 서버로 보내는 data타입을 json이라고 알려줘야한다 그냥 json을 보내면 제대로 인식을 못한다 MIME타입으로 보내줘야 한다 application/json 이런식으로 컨트롤러에서도 새로운 애너테이션이 필요하다
			//응답
			dataType: 'json', // 받아오는 데이터 타입도 제이슨
			success: function(resData){ 	// resData = {"name": "민경태", "tel": "010-1234-1234"}
				let str = '<ul>';
				str += '<li>' + resData.name;
				str += '<li>' + resData.tel; // 지가 알아서 닫는다 태그를 
				$('#result').html(str); //append는 원래 empty로 초기화를 해줬는데 원래있던거에 추가를 하는거라서 html은 그냥 내용을 갈아끼우는거다(덮어쓰기) 
			},
			error: function(jqXHR){
				if(jqXHR.status == 400){
					alert('이름과 전화번호는 필수입니다.');
				}
			}
		})
	}
	
	function fn2(){
		$.ajax({
			// 요청
			type: 'post',
			url: '${contextPath}/third/ajax2',
			data: JSON.stringify({
				'name': $('#name').val(),
				'tel': $('#tel').val()
			}),
			contentType: 'application/json', 
			// 응답
			dataType: 'json',
			success: function(resData){
				let str = '<ul>';
				str += '<li>' + resData.name;
				str += '<li>' + resData.tel;
				$('#result').html(str);
			},
			error: function(jqXHR){
				if(jqXHR.status == 400){
					alert('이름과 전화번호 입력을 확인하세요.');
				}
			}
		})
	}
</script>
</head>
<body>

	<div>
		<form id="frm">
			<div>
				<label for="name">이름</label>
				<input id="name" name="name">
			</div>
			<div>
				<label for="tel">전화번호</label>
				<input id="tel" name="tel">
			</div>
			<div>
				<input type="button" value="전송1" onclick="fn1()">
				<input type="button" value="전송2" onclick="fn2()">
			</div>
		</form>
	</div>
	
	<hr>
	
	<div id="result"></div>
	
</body>
</html>
