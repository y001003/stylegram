<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>안내</title>
<link rel="stylesheet" 
   href="${pageContext.request.contextPath}/resources/css/layout.css">
</head>
<body>
<div class="page-main-style">
	<h2>안내</h2>
	<div class="result-display">
		잘못된 접속입니다.<br>
		<input type="button" value="홈으로"
		   onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	</div>
</div>
</body>
</html>




