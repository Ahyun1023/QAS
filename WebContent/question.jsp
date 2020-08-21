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
<link href="../css/question.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet" type="text/css">
<script src="../js/question.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<meta charset="UTF-8">
<title>QAS</title>
</head>
<body>
	<input type="hidden" id="isLogin" value=${isLogin } />
	<c:forEach var="question" items="${question }">
		<c:set var="isSelect" value="${question.selection }"/>
		<c:set var="select_userId" value="${question.select_userId }"/>
		<c:set var="qUserid" value="${question.userId }" />
		<!-- <input type="hidden" value="question.selection" />-->
		<h1>
			Q.<span id="Qtitle${question.id }">${question.title }</span>
			<c:if test="${isSelect == 1 }">
				<span id="selectedMark"> - 채택 완료</span>
			</c:if>
		</h1>
		<input type="hidden" value="${question.id }" id="questionId" />
		<input type="hidden" value="${question.category }" id="questionCategory" />
		<p>
			게시글 아이디: ${question.id } |
			분류: ${question.category } |
			질문자: ${question.userId } |
			조회수: ${question.view } |
			작성일: ${question.created }
			<c:if test="${qUserid == sessionId  && isSelect != 1}">
			| <input type="button" value="수정하기" id="QmodifiedButton" onclick="modifiedQuestion(${question.id})" /> | 
			<input type="button" value="삭제하기" onclick="deleteQuestion(${question.id})"/>
			</c:if>
		</p>

		<hr />
		<span id="Qcontent${question.id }">${question.content }</span>
	</c:forEach>
	
		<hr />
		
		<c:if test="${qUserid != sessionId && isSelect != 1}">
			<c:choose>
				<c:when test="${qUserid == '(삭제된 이용자)' }">
					<p style="color: red;">삭제된 이용자의 질문에는 답변을 달 수 없습니다.</p>
					<hr />
				</c:when>
				
				<c:otherwise>
					<h3>내 답변</h3>
					<c:forEach var="question" items="${question }">
						<input type="hidden" value="${question.id }" id="answerQid" name="answerQid" />
					</c:forEach>
					<p>제목: <input type="text" id="answerTitle" name="answerTitle" /></p>
					<p><textarea name="answerContent" id="answerContent" cols="30" rows="10"></textarea></p>
					<input type="button" value="답변하기" onclick="writeAns()" />
					<hr />
				</c:otherwise>
			</c:choose>
		</c:if>
		
		<c:choose>
			<c:when test="${answer.size() == 0 }">
			<input type="hidden" id="isAnswer" value="false" />
				<p>아직 작성된 답변이 없습니다.</p>
			</c:when>
			
			<c:otherwise>
			<input type="hidden" id="isAnswer" value="true" />
			<h3>작성된 답변 ${answer.size()}개</h3>
			<c:if test="${isSelect == 1 }"> <!-- 채택된 답변이 있을 경우 가장 위로 올림-->
				<c:forEach var="answer" items="${answer }"> 
					<c:if test="${select_userId == answer.userId }">
						<p id="Aupper">
							A. <span id="Atitle${answer.id }">${answer.title } - 채택됨</span>
							<input type="hidden" id="answerId" value="${answer.id }" />
						</p>
						<p id="AUserid">${answer.userId }님의 답변</p>
						<p id="Acontent${answer.id }">${answer.content }</p>
						<p>작성 날짜: ${answer.created }</p>
						<hr>
					</c:if>
				</c:forEach>
			</c:if>
			
			<c:forEach var="answer" items="${answer }"> <!-- 달린 답변들 -->
				<c:if test="${select_userId !=  answer.userId}">
					<c:if test ="${answer.userId == sessionId }">	
						<input type="hidden" id="isUserid" value="${sessionId }" />	
					</c:if>
					<p id="Aupper">
						A. <span id="Atitle${answer.id }">${answer.title }</span>
						<input type="hidden" id="answerId" value="${answer.id }" />
						<c:if test="${qUserid == sessionId && isSelect != 1 && answer.userId != '(삭제된 이용자)'}">
							<input type="button" value="채택하기" onclick="selectionAnswer('${answer.userId}')"/>
						</c:if>
						<c:if test="${answer.userId == '(삭제된 이용자)' }">
							<span id="deleted_warning">삭제된 이용자의 답변은 채택할 수 없습니다.</span>
						</c:if>
						<c:if test="${answer.userId == sessionId  && isSelect != 1}">
							<input type="button" id="AmodifiedButton" value="수정하기" onclick="modifiedAnswer(${answer.id})" />
							<input type="button" value="삭제하기" onclick="deleteAnswer(${answer.id})" />
						</c:if>
					</p>
					<p id="AUserid">${answer.userId }님의 답변</p>
					<p id="Acontent${answer.id }">${answer.content }</p>
					<p>작성 날짜: ${answer.created }</p>
					<hr />
				</c:if>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</body>
</html>