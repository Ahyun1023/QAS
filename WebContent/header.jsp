<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QAS</title>
<c:choose>
	<c:when test="${otherJsp == 'true'}">
		<link href="../css/common.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
		<script src="../js/common.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
		<link href="../css/header.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
		<script src="../js/header.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
	</c:when>
	<c:otherwise>
		<link href="css/common.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
		<script src="js/common.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
		<link href="css/header.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
		<script src="js/header.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
	</c:otherwise>
</c:choose>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<div id="header">
		<input type="hidden" id="isLogin" value=${isLogin } />
		<input type="hidden" id="userId" value=${sessionId } />
		<c:choose>
			<c:when test="${otherJsp == 'true'}">
				<span class="logo"><a href="/test/main"><img src="../image/logo.png"></a></span>
			</c:when>
			<c:otherwise>
				<span class="logo"><a href="/test/main"><img src="./image/logo.png"></a></span>
			</c:otherwise>
		</c:choose>
		
		<c:if test="${sessionName != null}">
			<div class="userInfo">
				<p>${sessionName }</p>
				<p>현재 등급: ${sessionGrade }</p>
				<p><a href="/test/profile?userId=${sessionId}" class="GoProfile">프로필 바로가기 ></a></p>	
			</div>
		</c:if>
		<br><br>
		<form action="/test/search/find.do" method="get" class="search">
			<select name="category" id="HeaderCategory">
				<option value="">전부</option>
					<option value="교육">교육</option>
					<option value="게임">게임</option>
					<option value="건강">건강</option>
					<option value="여행">여행</option>
					<option value="쇼핑">쇼핑</option>
					<option value="생활">생활</option>
				</select>
			<input type="text" placeholder="검색" id="word" name="word" required/>
			<input type="submit" value="검색하기" class="searchButton"/>
		</form>
		<br><br><br><br>
		<div class="headerBar">
			<hr class="headerHr">
			<input type="button" onclick="locMyProfile()" value="내 프로필" class="headerButton"/>
			<input type="button" onclick="locQuestion()" value="질문 올리기" class="headerButton"/>
			<input type="button" onclick="locCategory()" value="카테고리" class="headerButton" />
			<input type="button" onclick="locLogin()" value="로그인" class="headerButton" id="loginButton">
			<input type="button" onclick="logout()" value="로그아웃" class="headerButton" id="logoutButton">
			<input type="button" onclick="locSignup()" value="회원가입"class="headerButton" id="signupButton">
			<hr class="headerHr">
		</div>
	</div>
</body>
</html>