<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QAS</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="js/common.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<script src="js/profile.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>
<body>
	<c:forEach var="userInfo" items="${userInfo }">
		<c:set var="thisUserId" value="${userInfo.id}"/>
		<input type="hidden" id="thisUserId" value="${$userInfo.id }" />
		<input type="hidden" id="myId" value="${sessionId }" />
		<h1>${userInfo.id}님의 프로필</h1>
		<h3>${userInfo.id}님은 현재 ${userInfo.grade}등급 입니다.</h3>
		<h3>현재 포인트: ${userInfo.point }</h3>
		<c:choose>
			<c:when test="${userInfo.interests == '' }">
				<p>관심분야가 없습니다.</p>
			</c:when>
			<c:otherwise>
				<p>관심 분야: ${userInfo.interests }</p>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${userInfo.introduce == '' }">
				<p>소개글이 없습니다.</p>
			</c:when>
			<c:otherwise>
				<p>소개글: ${userInfo.introduce }</p>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<p>작성한 질문 수: ${myQuestions.size()}</p>
	<p>작성한 답변 수: ${answeringQuestions.size()}</p>
	<p>채택된 수: ${selectedQuestions.size() }</p>
	<c:if test="${param.userId == sessionId }">
		<a href="/test/change_info.jsp">정보 수정하기</a>
		<a href="/test/signout.jsp">회원탈퇴하기</a>
	</c:if>
	<c:if test="${param.userId != sessionId }">
		<input type="submit" id="requestQuestion" value="이 유저에게 질문하기" onclick='requestQ("${thisUserId}")'/>
	</c:if>
	<h1>작성한 질문</h1>
	<c:choose>
		<c:when test="${myQuestions.size() == 0 }">
			<p>아직 작성한 질문이 없습니다.</p>
		</c:when>
		<c:otherwise>
		<a href="/test/userinfo/questionList.do?userId=${thisUserId}">+더보기</a>
		<table border="1">
			<tr align="center">
				<td><b>질문글 아이디</b></td>
				<td><b>제목</b></td>
				<td><b>카테고리</b></td>
				<td><b>조회수</b></td>
				<td><b>작성 날짜</b></td>
			</tr>
			<c:forEach var="myQ" items="${myQuestions }">
				<tr align="center">
					<td>${myQ.id }</td>
					<td><a href="/test/question/read.do?qid=${myQ.id}">${myQ.title}</a></td>
					<td>${myQ.category }</td>
					<td>${myQ.view }</td>
					<td>${myQ.created }</td>
				</tr>
			</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
		
	
	<h1>답변한 질문</h1>
	<c:choose>
		<c:when test="${answeringQuestions.size() == 0 }">
			<p>아직 작성한 질문이 없습니다.</p>
		</c:when>
		<c:otherwise>
		<a href="/test/userinfo/answerList.do?userId=${thisUserId }">+더보기</a>
		<table border="1">
			<tr align="center">
				<td><b>질문글 아이디</b></td>
				<td><b>제목</b></td>
				<td><b>카테고리</b></td>
				<td><b>조회수</b></td>
				<td><b>작성 날짜</b></td>
			</tr>
			<c:forEach var="answeringQ" items="${answeringQuestions }">
				<tr align="center">
					<td>${answeringQ.id }</td>
					<td><a href="/test/question/read.do?qid=${answeringQ.id }">${answeringQ.title }</a><br /></td>
					<td>${answeringQ.category }</td>
					<td>${answeringQ.view }</td>
					<td>${answeringQ.created }</td>
				</tr>
			</c:forEach>
		</table>
		</c:otherwise>
	</c:choose>

	<h1>채택된 질문</h1>
	<c:choose>
		<c:when test="${selectedQuestions.size() == 0 }">
			<p>아직 채택된 답변이 없습니다.</p>
		</c:when>
		<c:otherwise>
		<a href="/test/userinfo/selectedList.do?userId=${thisUserId }">+더보기</a>
		<table border="1">
			<tr align="center">
				<td><b>질문글 아이디</b></td>
				<td><b>제목</b></td>
				<td><b>카테고리</b></td>
				<td><b>조회수</b></td>
				<td><b>작성 날짜</b></td>
			</tr>
			<c:forEach var="selectedQ" items="${selectedQuestions }">
					<tr align="center">
						<td>${selectedQ.id }</td>
						<td>Q.<a href="/test/question/read.do?qid=${selectedQ.id }">${selectedQ.title }</a></td>
						<td>${selectedQ.category }</td>
						<td>${selectedQ.view }</td>
						<td>${selectedQ.created }</td>
					</tr>
			</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	
	<c:if test="${param.userId == sessionId }">
		<h1>나에게 온 질문</h1>
		<c:choose>
			<c:when test="${selectedQuestions.size() == 0 }">
				<p>나에게 온 질문이 없습니다.</p>
			</c:when>
			<c:otherwise>
			<a href="/test/userinfo/responseList.do?userId=${thisUserId }">+더보기</a>
			<table border="1">
				<tr align="center">
					<td><b>질문글 아이디</b></td>
					<td><b>제목</b></td>
					<td><b>카테고리</b></td>
					<td><b>조회수</b></td>
					<td><b>작성 날짜</b></td>
				</tr>
				<c:forEach var="responseQ" items="${responseQuestions }">
						<tr align="center">
							<td>${responseQ.id }</td>
							<td>Q.<a href="/test/question/read.do?qid=${responseQ.id }">${responseQ.title }</a></td>
							<td>${responseQ.category }</td>
							<td>${responseQ.view }</td>
							<td>${responseQ.created }</td>
						</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	</c:if>

</body>
</html>