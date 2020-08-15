<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QAS</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="js/common.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>
<body>
	<input type="hidden" id="isLogin" value=${isLogin } />
	<h1>안녕하세요, ${sessionName}.</h1>
	<h3>${sessionName}님의 등급은${sessionGrade}입니다.</h3>
	<a href="./change_info.jsp">정보 수정하기</a>
	<a href="./signout.jsp">회원탈퇴하기</a>
	<a href="./main.jsp">메인으로 돌아가기</a>
	<h1>내가 작성한 질문</h1>
	<h1>내가 답변한 질문</h1>
</body>
</html>