<%@ page language="java" contentType="text/html; charset=UTF-8"
 	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/search.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet" type="text/css">
<script src="../js/search.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<title>QAS</title>
</head>
<body>
	<c:set var="otherJsp" value="true" />
	<%@ include file="header.jsp" %>
	<span class='titles'>검색 결과</span>
	<span class='listCount'>${searchList.size() }개</span>
	<div class='tables'>
		<table>
			<tr align="center">
				<td><b>질문글 아이디</b></td>
				<td><b>제목</b></td>
				<td><b>카테고리</b></td>
				<td><b>질문자</b></td>
				<td><b>조회수</b></td>
				<td><b>작성 날짜</b></td>
			</tr>
	
			<c:if test="${searchList == null }">
					<tr>
					<td colspan="5">
						<b>검색 결과가 없습니다.</b>
					</td>
				</tr>
			</c:if>

			<c:if test="${searchList != null }">
				<c:forEach var="search" items="${searchList }">
					<tr align="center">
						<td>${search.id }</td>
						<td><a href="/test/question/read.do?qid=${search.id }">${search.title }</a></td>
						<td>${search.category }</td>
						<td>${search.userId }</td>
						<td>${search.view }</td>
						<td>${search.created }</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>
</body>
</html>