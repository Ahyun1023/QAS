<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/signout.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet" type="text/css">
<script src="./js/signout.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<title>QAS</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<input type="hidden" id="isLogin" value=${isLogin } />
	<div class='signout'>
		<h1>회원 탈퇴</h1>

		<div id="signout_terms">
			<p>1. 모든 회원 정보가 삭제됩니다.</p>
			<span>탈퇴한 아이디로 다시 회원가입 할 수 있습니다.</span>
			<p>2. 질문, 답변 등의 서비스를 이용할 수 없습니다.</p>
			<span>다른 사용자가 올린 질문, 답변을 보는 것은 가능합니다.</span>
			<p>3. 탈퇴 후 작성했던 질문과 답변은 삭제되지 않습니다.</p>
			<span>탈퇴한 사용자의 정보는 제공되지 않습니다.</span>
		</div>
		<label id="termsCheckLabel"><input type="checkbox" id="termsCheck"><span class='required'>*</span>안내 사항을 모두 확인하였으며, 이에 동의합니다.</label>

		<form name="SignoutForm" class='signoutForm'>
			<p class='signoutTitle'><span class='required'>*</span>아이디</p>
			<p><input type="text" id="id" maxlength=8></p>
			<p class='signoutTitle'><span class='required'>*</span>비밀번호</p>
			<p><input type="password" id="pw" maxlength=16></p>
			<p class='signoutTitle'><span class='required'>*</span>비밀번호 확인</p>
			<p><input type="password" id="pw_check" maxlength=16></p>
			<input type="button" value="회원 탈퇴" onclick="signout()" class='submitButton'> 
			<input type="button" value="취소" onclick="locMain()" class='backButton'>
		</form>
	</div>
</body>
</html>