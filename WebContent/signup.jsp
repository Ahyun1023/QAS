<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/signup.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet" type="text/css">
<script src="./js/common.js?ver=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<script src="./js/signup.js?ver=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<meta charset="UTF-8">
<title>QAS</title>
</head>
<body>
	<h1>회원가입</h1>
	<div id="signup_terms">
		약관들... <!-- 나중에 할래 -->
	</div>
	<form name="signupForm">
		<p>아이디</p>
		<p>4~8자의 영문과 숫자만 입력할 수 있습니다.</p>
		<p><input type="text" id="id" /></p>
		<span id="isIdExist"></span>
		<p>비밀번호</p>
		<p>8~16자의 영문과 숫자만 입력할 수 있습니다.</p>
		<p><input type="password" id="pw" /></p>
		<p>비밀번호 확인</p>
		<p><input type="password" id="pw_check" /></p>
		<span id="isPwTrue"></span>
		<p>이름</p>
		<p>2~8자의 한글만 입력할 수 있습니다.</p>
		<p><input type="text" id="name" /></p>
		<p>이메일</p>
		<p>
			<input type="text" id="email" />@
			<select name="emailForm" id="emailForm">
				<option value="">--선택--</option>
				<option value="gmail.com">gmail.com</option>
            	<option value="naver.com">naver.com</option>
            	<option value="daum.net">daum.net</option>
            	<option value="nate.com">nate.com</option>
			</select>
		</p>
		<p>관심 분야</p>
		<p>
			<select name="interests" id="interests">
				<option value="">--선택--</option>
				<option value="교육">교육</option>
				<option value="게임">게임</option>
				<option value="건강">건강</option>
				<option value="여행">여행</option>
				<option value="쇼핑">쇼핑</option>
				<option value="생활">생활</option>
			</select>
		</p>
		<p><input type="button" value="회원 가입" onclick="signup()"/></p>
		<input type="button" value="로그인 페이지로 이동" onclick="location.href='/test/login.jsp'"/>
		<input type="button" value="메인 페이지로 이동" onclick="location.href='/test/main'"/>
	</form>
</body>
</html>