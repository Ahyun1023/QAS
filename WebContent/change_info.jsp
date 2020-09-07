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
<link href="css/change_info.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
<title>QAS</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<c:if test="${isLogin != true }">
		<jsp:forward page="login.jsp"/>
	</c:if>
	<input type="hidden" id="isLogin" value=${isLogin }>
	<input type="hidden" id="userId" value=${sessionId }>

	<form name="changeForm" class='changeForm'>
		<h1>회원 정보 수정</h1>
		<p class='notice'><span class=required>*</span>표시가 있는 항목은 필수 항목 입니다.</p>
		<p class='changeTitle'><span class='required'>*</span>비밀번호</p>
		<p class='changehint'>8~16자의 영문과 숫자만 입력할 수 있습니다.</p>
		<p><input type="password" id="pw" placeholder='비밀번호' maxlength=16 required/></p>
		<p class='changeTitle'><span class='required'>*</span>비밀번호 확인</p>
		<p><input type="password" id="pw_check" placeholder='비밀번호 확인' maxlength=16 required/></p>
		<span id="isPwTrue"></span>
		<p class='changeTitle'><span class='required'>*</span>이름</p>
		<p class='changehint'>2~8자의 한글만 입력할 수 있습니다.</p>
		<p><input type="text" id="name" value="${sessionName }" placeholder='이름' required></p>
		<p class='changeTitle'><span class='required'>*</span>이메일</p>
		<p>
			<input type="text" id="email" value="${sessionEmail }" class='emailInput' placeholder='이메일' required>&nbsp;@
			<select name="emailForm" id="emailForm" class='select'>
				<option value="gmail.com" <%if(emailForm.equals("gmail.com")){%>selected<%}%>>gmail.com</option>
            	<option value="naver.com" <%if(emailForm.equals("naver.com")){%>selected<%}%>>naver.com</option>
            	<option value="daum.net" <%if(emailForm.equals("daum.com")){%>selected<%}%>>daum.net</option>
            	<option value="nate.com" <%if(emailForm.equals("nate.com")){%>selected<%}%>>nate.com</option>
			</select>
		</p>
		<p class='changeTitle'>관심 분야</p>
		<p>
			<select name="interests" id="interests" class='select'>
				<option value="" <%if(interests.equals(null)){%> selected<%}%>>--선택--</option>
				<option value="교육" <%if(interests.equals("교육")){%> selected<%}%>>교육</option>
				<option value="게임" <%if(interests.equals("게임")){%> selected<%}%>>게임</option>
				<option value="건강" <%if(interests.equals("건강")){%> selected<%}%>>건강</option>
				<option value="여행" <%if(interests.equals("여행")){%> selected<%}%>>여행</option>
				<option value="쇼핑" <%if(interests.equals("쇼핑")){%> selected<%}%>>쇼핑</option>
				<option value="생활" <%if(interests.equals("생활")){%> selected<%}%>>생활</option>
			</select>
		</p>
		<p class='changeTitle'>자기 소개</p>
		<input type="text" id="introduce" value='${sessionIntroduce }' placeholder='자기 소개'>
		<p><input type="button" value="회원 정보 변경" class='submitButton' onclick="change_info()"/></p>
		<p><input type="button" id="backButton" class='backButton' onclick="goBack()" value="뒤로가기" /></p>
	</form>
</body>
</html>