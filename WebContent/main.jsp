<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<link href="css/main.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet" type="text/css">
<script src="js/main.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<meta charset="UTF-8">
<title>QAS</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<h1 class='mainH1'>오늘의 질문</h1>
	<c:choose>
		<c:when test="${todayQ.size() == 0  }">
			<p class='noneContent'>아직 작성된 질문이 없습니다.</p>
			<p class='noneContent'><a href="/test/write_question.jsp">>> 먼저 오늘의 질문을 작성해주세요!</a></p>
		</c:when>
		<c:otherwise>
				<c:forEach var="todayQ" items="${todayQ }">
				<div class='contentDiv' onmouseenter="zoomIn(event)" onmouseleave="zoomOut(event)" onclick="locationQuestion(${todayQ.id})">
					<p class='Questioner'><a href="/test/profile?userId=${todayQ.userId }">${todayQ.userId}님의 질문</a></p>
					<p class='Qtitle'><a href="/test/question/read.do?qid=${todayQ.id }">Q. ${todayQ.title}</a></p>
					<p class="Qcontent">${todayQ.content}</p>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<br class='clear'><br />
	<hr class='mainHr'>
	
	<h1 class='mainH1'>많은 사람들이 본 질문</h1>
	<c:choose>
		<c:when test="${moreViewQ.size() == 0  }">
			<p class='noneContent'>아직 작성된 질문이 없습니다.</p>
		</c:when>
		<c:otherwise>
				<c:forEach var="moreViewQ" items="${moreViewQ }">
				<div class='contentDiv' onmouseenter="zoomIn(event)" onmouseleave="zoomOut(event)" onclick="locationQuestion(${moreViewQ.id})">
					<p class='Questioner'><a href="/test/profile?userId=${moreViewQ.userId }">${moreViewQ.userId}님의 질문</a></p>
					<p class='Qtitle'><a href="/test/question/read.do?qid=${moreViewQ.id }">Q. ${moreViewQ.title}</a></p>
					<p class="Qcontent" style="text-overflow: ellipsis; overflow:hidden;">${moreViewQ.content}</p>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<br class='clear'><br />
	<hr class='mainHr'>
	
	<h1 class='mainH1'>조회수가 적은 질문</h1>
	<c:choose>
		<c:when test="${lessViewQ.size() == 0  }">
			<p class='noneContent'>아직 작성된 질문이 없습니다.</p>
		</c:when>
		<c:otherwise>
				<c:forEach var="lessViewQ" items="${lessViewQ }">
				<div class='contentDiv' onmouseenter="zoomIn(event)" onmouseleave="zoomOut(event)" onclick="locationQuestion(${lessViewQ.id})">
					<p class='Questioner'><a href="/test/profile?userId=${lessViewQ.userId }">${lessViewQ.userId}님의 질문</a></p>
					<p class='Qtitle'><a href="/test/question/read.do?qid=${lessViewQ.id }">Q. ${lessViewQ.title}</a></p>
					<p class="Qcontent">${lessViewQ.content}</p>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<br class='clear'><br />
	<hr class='mainHr'>
	
	<h1 class='mainH1'>내 관심 분야 질문</h1>
	<c:choose>
		<c:when test="${isLogin != true }">
			<p class='noneContent'>로그인 이후 이용할 수 있는 서비스입니다.</p>
			<input type="button" value="로그인" onclick="locLogin()" id="loginButton2"/>
		</c:when>
		<c:when test="${myInterestQ.size() == 0  }">
			<c:choose>
				<c:when test="${sessionInterests == '' }">
					<p>아직 관심 분야가 등록되지 않았습니다.</p>
					<p><a href="/test/write_question.jsp">당신의 관심 분야에 대한 질문을 작성해주세요!</a></p>
				</c:when>
				<c:otherwise>
					<p>아직 작성된 질문이 없습니다.</p>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
				<c:forEach var="myInterestQ" items="${myInterestQ }">
				<div class='contentDiv' onmouseenter="zoomIn(event)" onmouseleave="zoomOut(event)" onclick="locationQuestion(${myInterestQ.id})">
					<p class='Questioner'><a href="/test/profile?userId=${myInterestQ.userId }">${myInterestQ.userId}님의 질문</a></p>
					<p class='Qtitle'><a href="/test/question/read.do?qid=${myInterestQ.id }">Q. ${myInterestQ.title}</a></p>
					<p class="Qcontent">${myInterestQ.content}</p>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<br class='clear'><br />
	<hr class='mainHr'>
	
	<h1 class='mainH1'>👑베스트 답변인👑</h1>
	<p class='bestAnswerInfo'>베스트 답변인에 오르려면 최소 <span class='bestAnswerPoint'>등급이 5 이상이며, 같은 분야 질문에서 많은 채택</span>을 받으면 오를 수 있습니다.</p>
	<c:choose>
		<c:when test="${bestAnswererList.size() == 0 }">
			<p class='noneContent'>아직 베스트 답변인이 없습니다. 질문에 답변을 달아 많은 채택을 받아보세요!</p>
		</c:when>
		<c:otherwise>
			<c:forEach var="bestAnswererList" items="${bestAnswererList }">
			<div class='userInfoDiv' onclick="locationUserProfile('${bestAnswererList.id}')">
				<p class='AnswererId'><a href="/test/profile?userId=${bestAnswererList.id }">${bestAnswererList.id }</a></p>
				<p class='AnswererInterest'>관심 분야: ${bestAnswererList.interests }</p>
				<p class='AnswererGrade'>등급: ${bestAnswererList.grade }</p>
				<p class='AnswererIntroduce'>한 줄 소개: ${bestAnswererList.introduce }</p>
			</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</body>
</html>