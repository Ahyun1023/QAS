<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
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
	<c:set var="otherJsp" value="true" />
	<%@ include file="header.jsp" %>
	<c:forEach var="question" items="${question }">
		<c:set var="isSelect" value="${question.selection }"/>
		<c:set var="select_userId" value="${question.select_userId }"/>
		<c:set var="qUserid" value="${question.userId }" />
		<h1 class='Qtitle'>
			Q. <span id="Qtitle${question.id }">${question.title }</span>
			<c:if test="${isSelect == 1 }">
				<span id="selectedMark"> - 마감 </span>
			</c:if>
		</h1>
		<input type="hidden" value="${question.id }" id="questionId" />
		<input type="hidden" value="${question.category }" id="questionCategory" />
		<p class='QInfo'>
			게시글 아이디: ${question.id } |
			분류: ${question.category } |
			질문자: <a href="/test/profile?userId=${question.userId }">${question.userId }</a> |
			조회수: ${question.view } |
			작성일: ${question.created }
			<c:if test="${qUserid == sessionId  && isSelect != 1}">
			| <input type="button" value="수정하기" id="QmodifiedButton" class='updateButton' onclick="modifiedQuestion(${question.id})" /> | 
			<input type="button" value="삭제하기" class='deleteButton' onclick="deleteQuestion(${question.id})"/>
			</c:if>
		</p>
		<c:if test="${question.request_user != null }">
		<input type="hidden" id="request_user" value="${question.request_user }" />
			<c:choose>
				<c:when test="${question.request_user == sessionId }">
					<p class='requestUser'>나에게 묻는 질문</p>
				</c:when>
				<c:otherwise>
					<p class='requestUser'>${question.request_user }님에게 묻는 질문</p>
				</c:otherwise>
			</c:choose>
		</c:if>
		
		
		<hr class='questionHr'>
		<div id="Qcontent${question.id }" class='Qcontent'>${question.content }</div>
	</c:forEach>
		<hr class='questionHr'>
		
		<c:if test="${qUserid != sessionId && isSelect != 1}">
			<c:choose>
				<c:when test="${qUserid == '(삭제된 이용자)' }">
					<p class='warning'>삭제된 이용자의 질문에는 답변을 달 수 없습니다.</p>
					<hr />
				</c:when>
				
				<c:otherwise>
					<div class='myAnswerDiv'> <!-- 답변 작성 칸 -->
						<c:forEach var="question" items="${question }">
							<input type="hidden" value="${question.id }" id="answerQid" name="answerQid" />
						</c:forEach>
						<p><span class='Atitle'>A. </span><input type="text" id="answerTitle" name="answerTitle" class='answerTitle' maxlength=50/></p>
						<p class='counting'><span id='title_count'>0</span>/50</p>
						<p><textarea name="answerContent" id="answerContent" cols="30" rows="10" class='answerContent' maxlength=1500></textarea></p>
						<p class='counting'><span id='content_count'>0</span>/1500</p>
						<input type="button" value="답변하기" class='answerButton' onclick="writeAns()" />
					</div>
					<hr style='border: 1px solid gray;' />
				</c:otherwise>
			</c:choose>
		</c:if>
		
		<c:choose>
			<c:when test="${answer.size() == 0 }">
			<input type="hidden" id="isAnswer" value="false" />
				<p class='warning'>아직 작성된 답변이 없습니다.</p>
			</c:when>
			
			<c:otherwise>
			<input type="hidden" id="isAnswer" value="true" />
			<h3 class='answerCount'>작성된 답변 ${answer.size()}개</h3>
			<hr style='border: 2px solid gray;' />
			<c:if test="${isSelect == 1 }"> <!-- 채택된 답변이 있을 경우 가장 위로 올림-->
				<c:forEach var="answer" items="${answer }"> 
					<c:if test="${select_userId == answer.userId }">
						<p id="Aupper">
							<span class='selectedTitle'>A. <span id="Atitle${answer.id }" >${answer.title } ✅</span></span>
							<input type="hidden" id="answerId" value="${answer.id }" />
						</p>
						<p id="AUserid"><a href="/test/profile?userId=${answer.userId }" >${answer.userId }님의 답변</a></p>
						<p id="Acontent${answer.id }" class='Acontent'>${answer.content }</p>
						<p class='Acreated'>작성 날짜: ${answer.created }</p>
						<hr style='border: 2px solid gray;'>
					</c:if>
				</c:forEach>
			</c:if>
			
			<c:forEach var="answer" items="${answer }"> <!-- 달린 답변들 -->
				<c:if test="${select_userId !=  answer.userId}">
					<c:if test ="${answer.userId == sessionId }">	
						<input type="hidden" id="isUserid" value="${sessionId }" />	
					</c:if>
					<p id="Aupper">
						<span class='answersTitle'><span class='Atitle'>A. </span><span id="Atitle${answer.id }">${answer.title }</span></span>
						<input type="hidden" id="answerId" value="${answer.id }" />
						<c:if test="${qUserid == sessionId && isSelect != 1 && answer.userId != '(삭제된 이용자)'}">
							<input type="button" value="채택하기" class='selectButton' onclick="selectionAnswer('${answer.userId}')"/>
						</c:if>
						<c:if test="${answer.userId == '(삭제된 이용자)' }">
							<span id="deleted_warning">삭제된 이용자의 답변은 채택할 수 없습니다.</span>
						</c:if>
						<c:if test="${answer.userId == sessionId  && isSelect != 1}">
							<input type="button" id="AmodifiedButton" class='updateButton' value="수정하기" onclick="modifiedAnswer(${answer.id})" />
							<input type="button" value="삭제하기" class='deleteButton' onclick="deleteAnswer(${answer.id})" />
						</c:if>
					</p>
					<p id="AUserid"><a href="/test/profile?userId=${answer.userId }" >${answer.userId }님의 답변</a></p>
					<p id="Acontent${answer.id }" class='Acontent'>${answer.content }</p>
					<p class='Acreated'>작성 날짜: ${answer.created }</p>
					<hr />
				</c:if>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</body>
</html>