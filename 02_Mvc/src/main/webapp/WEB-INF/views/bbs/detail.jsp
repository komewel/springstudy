<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>번호 ${bbs1.bbsNo}</h1>
		<div>제목: ${bbs1.title}</div>
		<div>작성일: ${bbs1.createdAt}</div>
	</div>
	<div>
		<h1>번호 ${bbs2.bbsNo}</h1>
		<div>제목: ${bbs2.title}</div>
		<div>작성일: ${bbs2.createdAt}</div>
	</div>
</body>
</html>