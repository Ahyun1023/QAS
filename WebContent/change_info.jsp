<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
	String interests = (String)session.getAttribute("sessionInterests");
	String emailForm = (String)session.getAttribute("sessionEmailForm");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="js/change_info.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<title>QAS</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<c:if test="${isLogin != true }">
		<jsp:forward page="login.jsp"/>
	</c:if>
	<input type="hidden" id="isLogin" value=${isLogin } />
	<input type="hidden" id="userId" value=${sessionId }>
	<h1>회원 정보 수정</h1>
	<form name="signupForm">		
		<p>비밀번호</p>
		<p>8~16자의 영문과 숫자만 입력할 수 있습니다.</p>
		<p><input type="password" id="pw"/></p>
		<p>비밀번호 확인</p>
		<p><input type="password" id="pw_check" /></p>
		<span id="isPwTrue"></span>
		<p>이름</p>
		<p>2~8자의 한글만 입력할 수 있습니다.</p>
		<p><input type="text" id="name" value="${sessionName }"/></p>
		<p>이메일</p>
		<p>
			<input type="text" id="email" value="${sessionEmail }" />@
			<select name="emailForm" id="emailForm">
				<option value="gmail.com" <%if(emailForm.equals("gmail.com")){%>selected<%}%>>gmail.com</option>
            	<option value="naver.com" <%if(emailForm.equals("naver.com")){%>selected<%}%>>naver.com</option>
            	<option value="daum.net" <%if(emailForm.equals("daum.com")){%>selected<%}%>>daum.net</option>
            	<option value="nate.com" <%if(emailForm.equals("nate.com")){%>selected<%}%>>nate.com</option>
			</select>
		</p>
		<p>관심 분야</p>
		<p>
			<select name="interests" id="interests">
				<option value="" <%if(interests.equals(null)){%> selected<%}%>>--선택--</option>
				<option value="교육" <%if(interests.equals("교육")){%> selected<%}%>>교육</option>
				<option value="게임" <%if(interests.equals("게임")){%> selected<%}%>>게임</option>
				<option value="건강" <%if(interests.equals("건강")){%> selected<%}%>>건강</option>
				<option value="여행" <%if(interests.equals("여행")){%> selected<%}%>>여행</option>
				<option value="쇼핑" <%if(interests.equals("쇼핑")){%> selected<%}%>>쇼핑</option>
				<option value="생활" <%if(interests.equals("생활")){%> selected<%}%>>생활</option>
			</select>
		</p>
		<p>자기 소개</p>
		<input type="text" id="introduce" value=${sessionIntroduce }>
		<p><input type="button" value="회원 정보 변경" onclick="change_info()"/></p>
		<p><a href="/test/main">메인 페이지로 이동</a></p>
		<p><input type="button" id="backButton" onclick="goBack()" value="뒤로가기" /></p>
	</form>
</body>
</html>
