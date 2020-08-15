<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%>    
<!DOCTYPE html>
<html>
<head>
<script src="../js/question.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<meta charset="UTF-8">
<title>QAS</title>
</head>
<body>
	<input type="hidden" id="isLogin" value=${isLogin } />
	<c:forEach var="question" items="${question }">
	<c:set var="qUserid" value="${question.userId }" />
		<h1>Q.<span id="Qtitle${question.id }">${question.title }</span></h1>
		<input type="hidden" value="${question.id }" id="questionId" />
		게시글 아이디: ${question.id } |
		분류: ${question.category } |
		질문자: ${question.userId } |
		조회수: ${question.view } |
		작성일: ${question.created }
		<c:if test="${qUserid == sessionId }">
		| <input type="button" value="수정하기" id="QmodifiedButton" onclick="modifiedQuestion(${question.id})" /> | 
		<input type="button" value="삭제하기" onclick="deleteQuestion(${question.id})"/>
		</c:if> <br />
		<hr />
		<span id="Qcontent${question.id }">${question.content }</span>
	</c:forEach>
		<hr />
		<c:if test="${qUserid != sessionId}">
			<h3>내 답변</h3>
			<c:forEach var="question" items="${question }">
				<input type="hidden" value="${question.id }" id="answerQid" name="answerQid" />
			</c:forEach>
			제목: <input type="text" id="answerTitle" name="answerTitle" />
			<br /><br />
			
			<textarea name="answerContent" id="answerContent" cols="30" rows="10"></textarea><br />
			<input type="button" value="답변하기" onclick="writeAns()" />
			<hr />
		</c:if>
		<c:choose>
			<c:when test="${answer.size() == 0 }">
			<input type="hidden" id="isAnswer" value="false" />
				<p>아직 작성된 답변이 없습니다.</p>
			</c:when>
			<c:when test="${answer.size() != 0 }">
			<h3>작성된 답변 몇개</h3> <!-- 이건 나중에~ -->
			<input type="hidden" id="isAnswer" value="true" />
				<c:forEach var="answer" items="${answer }">
					<c:if test ="${answer.userId == sessionId }">	
						<input type="hidden" id="isUserid" value="${sessionId }" />	
					</c:if>
					<p id="Aupper" style="font-weight: bold; font-size:larger;">
						A. <span id="Atitle${answer.id }">${answer.title }</span>
						<input type="hidden" id="answerId" value="${answer.id }" />
						<c:if test="${qUserid == sessionId}">
							<input type="button" value="채택하기" onclick="selectionAnswer(${answer.id})"/>
						</c:if>
						<c:if test="${answer.userId == sessionId }">
							<input type="button" id="AmodifiedButton" value="수정하기" onclick="modifiedAnswer(${answer.id})" />
							<input type="button" value="삭제하기" onclick="deleteAnswer(${answer.id})" />
						</c:if>
					</p>
					<p id="AUserid" style="font-weight: bold;">${answer.userId }님의 답변</p>
					<p id="Acontent${answer.id }">${answer.content }</p>
					<br />
					작성 날짜: ${answer.created }
					<hr />
				</c:forEach>
			</c:when>
		</c:choose>
</body>
</html>