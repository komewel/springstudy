<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>이름: ${name}</h1>
	<h1>나이: ${age}</h1>
	
	<hr>
	
	<h1>이름: ${person.name}</h1> <!-- 객체이름 p는 속성이름 만들때 사용하지를 않는다, 객체명으로 소문자로 인식한다 @ModelAttribute(value="p")이걸 인수에 코드를 넣어주면 내가 원하는 속성값으로 인식해준다. -->
	<h1>나이: ${person.age}</h1>
	
	
</body>
</html>