<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/main.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet" type="text/css">
<script src="./js/main.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<meta charset="UTF-8">
<title>QAS</title>
</head>
<body>
	<input type="hidden" id="isLogin" value=${isLogin } />
	<input type="button" onclick="locMypage()" value="마이페이지" />
	<input type="button" onclick="locQuestion()" value="질문 올리기" />
	<input type="button" onclick="locLogin()" value="로그인" id="loginButton">
	<input type="button" onclick="logout()" value="로그아웃" id="logoutButton">
	<input type="button" onclick="locSignup()" value="회원가입" id="signupButton">
	<form action="/test/search/find.do" method="get">
		<select name="category" id="category">
			<option value="">전부</option>
			<option value="edu">교육</option>
			<option value="game">게임</option>
			<option value="health">건강</option>
			<option value="trip">여행</option>
			<option value="shopping">쇼핑</option>
			<option value="life">생활</option>
		</select>
		<input type="text" placeholder="검색" id="word" name="word" />
		<input type="submit" value="검색하기" />
	</form>
	<h1>오늘의 질문</h1>
		<c:choose>
		<c:when test="${todayQ.size() == 0  }">
			<p>아직 작성된 질문이 없습니다.</p>
		</c:when>
		<c:otherwise>
				<c:forEach var="todayQ" items="${todayQ }">
				<p>${todayQ.userId}님의 질문</p>
				<p><a href="/test/question/read.do?qid=${todayQ.id }">Q. ${todayQ.title}</a></p>
				<p id="Qcontent">${todayQ.content}</p>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	
	<h1>많은 사람들이 본 질문</h1>
	<c:choose>
		<c:when test="${moreViewQ.size() == 0  }">
			<p>아직 작성된 질문이 없습니다.</p>
		</c:when>
		<c:otherwise>
				<c:forEach var="moreViewQ" items="${moreViewQ }">
				<p>${moreViewQ.userId}님의 질문</p>
				<p><a href="/test/question/read.do?qid=${moreViewQ.id }">Q. ${moreViewQ.title}</a></p>
				<p id="Qcontent" style="text-overflow: ellipsis; overflow:hidden;">${moreViewQ.content}</p>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	
	<h1>조회수가 적은 질문</h1>
			<c:choose>
		<c:when test="${lessViewQ.size() == 0  }">
			<p>아직 작성된 질문이 없습니다.</p>
		</c:when>
		<c:otherwise>
				<c:forEach var="lessViewQ" items="${lessViewQ }">
				<p>${lessViewQ.userId}님의 질문</p>
				<p><a href="/test/question/read.do?qid=${lessViewQ.id }">Q. ${lessViewQ.title}</a></p>
				<p id="Qcontent">${lessViewQ.content}</p>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	
	<h1>내 관심 분야 질문</h1>
		<c:choose>
		<c:when test="${isLogin != true }">
			<p>로그인 이후 이용할 수 있는 서비스입니다.</p>
			<input type="button" value="로그인" onclick="locLogin()" id="loginButton2" />
		</c:when>
		<c:when test="${myInterestQ.size() == 0  }">
			<p>아직 작성된 질문이 없습니다.</p>
		</c:when>
		<c:otherwise>
				<c:forEach var="myInterestQ" items="${myInterestQ }">
				<p>${myInterestQ.userId}님의 질문</p>
				<p><a href="/test/question/read.do?qid=${myInterestQ.id }">Q. ${myInterestQ.title}</a></p>
				<p id="Qcontent">${myInterestQ.content}</p>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</body>
</html>