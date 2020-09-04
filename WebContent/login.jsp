<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="./js/login.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<meta charset="UTF-8">
<title>QAS</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<h1>로그인</h1>
	<form name="loginForm">
		아이디: <input type="text" id="id" maxlength=8/><br />
		비밀번호: <input type="password" id="pw" maxlength=16/><br />
		<input type="button" value="로그인" onclick="login()" />
	</form>
	<a href="/test/signup.jsp">회원가입하기</a>
</body>
</html>