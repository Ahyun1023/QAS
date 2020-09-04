<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../js/userInfoList.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<title>QAS</title>
</head>
<body>
	<c:set var="otherJsp" value="true" />
	<%@ include file="header.jsp" %>
	<table border="1">
		<tr align="center">
			<td><b>질문글 아이디</b></td>
			<td><b>제목</b></td>
			<td><b>카테고리</b></td>
			<td><b>질문자</b></td>
			<td><b>조회수</b></td>
			<td><b>작성 날짜</b></td>
		</tr>
		<c:choose>
			<c:when test="${infoList == null || infoList.size() == 0 }">
				<tr>
					<td colspan="5">
						<b>검색 결과가 없습니다.</b>
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="infoList" items="${infoList }">
					<tr align="center">
						<td>${infoList.id }</td>
						<td><a href="/test/question/read.do?qid=${infoList.id }">${infoList.title }</a></td>
						<td>${infoList.category }</td>
						<td>${infoList.userId }</td>
						<td>${infoList.view }</td>
						<td>${infoList.created }</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>