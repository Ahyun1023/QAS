<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="js/signout.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="js/common.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<title>QAS</title>
</head>
<body>
	<input type="hidden" id="isLogin" value=${isLogin } />
	<h1>회원 탈퇴</h1>
		<form name="SignoutForm">
		아이디: <input type="text" id="signout_id" /><br />
		비밀번호: <input type="password" id="signout_pw" /><br />
		비밀번호 확인: <input type="password" id="signout_pwcheck" /><br />
		<input type="button" value="로그인" onclick="signout()" />
		<input type="reset" value="다시 작성" />
	</form>
</body>
</html>