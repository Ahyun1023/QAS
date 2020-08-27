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
<script src="js/mypage.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>
<body>
	<input type="hidden" id="isLogin" value=${isLogin } />
	<h1>안녕하세요, ${sessionName}.</h1>
	<h3>${sessionName}님은 현재 ${sessionGrade}등급 입니다.</h3>
	<p>작성한 질문 수: ${myQuestions.size()}</p>
	<p>작성한 답변 수: ${answeringQuestions.size()}</p>
	<p>채택된 수: ${selectedQuestions.size() }</p>
	<a href="/test/change_info.jsp">정보 수정하기</a>
	<a href="/test/signout.jsp">회원탈퇴하기</a>
	<a href="/test/main">메인으로 돌아가기</a>
	<h1>내가 작성한 질문</h1>
	<c:choose>
		<c:when test="${myQuestions.size() == 0 }">
			<p>아직 작성한 질문이 없습니다.</p>
		</c:when>
		<c:otherwise>
			<c:forEach var="myQ" items="${myQuestions }">
				<p>Q.<a href="/test/question/read.do?qid=${myQ.id }">${myQ.title}</a></p>
				<p>카테고리: ${myQ.category }</p>
				<p>작성일: ${myQ.created }</p>
			</c:forEach>
		</c:otherwise>
		<p><a href="/test/myinfo/QuestionList.do">+더보기</a></p>
	</c:choose>
	<h1>내가 답변한 질문</h1>
		<c:choose>
		<c:when test="${answeringQuestions.size() == 0 }">
			<p>아직 작성한 답변이 없습니다.</p>
		</c:when>
		<c:otherwise>
			<c:forEach var="answeringQ" items="${answeringQuestions }">
				<c:forEach var="answerings" items="${answerings }">
					<p>Q.<a href="/test/question/read.do?qid=${answeringQ.id }">${answeringQ.title}</a></p>
					<p>A.${answerings.title}</p>
					<p>카테고리: ${answeringQ.category }</p>
					<p>작성일: ${answerings.created }</p>
				</c:forEach>
			</c:forEach>
		</c:otherwise>
		<p><a href="/test/myinfo/AnswerList.do">+더보기</a></p>
	</c:choose>
	<h1>답변이 채택된 질문</h1>
		<c:choose>
		<c:when test="${selectedQuestions.size() == 0 }">
			<p>아직 채택된 답변이 없습니다.</p>
		</c:when>
		<c:otherwise>
			<c:forEach var="selectedQ" items="${selectedQuestions }">
				<p>Q.<a href="/test/question/read.do?qid=${selectedQ.id }">${selectedQ.title}</a></p>
				<p>카테고리: ${selectedQ.category }</p>
				<p>작성일: ${selectedQ.created }</p>
			</c:forEach>
			<p><a href="/test/myinfo/SelectedList.do">+더보기</a></p>
		</c:otherwise>
	</c:choose>
</body>
</html>