<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="js/login.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<link href="css/login.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet" type="text/css">

<meta charset="UTF-8">
<title>QAS</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<form name="loginForm" class='loginForm'>
		<h1>로그인</h1>
		<p><input type="text" id="id" maxlength=8 class='idInput' placeholder='아이디'></p>
		<p><input type="password" id="pw" maxlength=16 class='pwInput' placeholder='비밀번호'></p>
		<input type="button" value="로그인" onclick="login()" class='loginButton'/>
		<p><a href="/test/signup.jsp" class='goSignUp'>회원가입하기</a></p>
	</form>
</body>
</html>