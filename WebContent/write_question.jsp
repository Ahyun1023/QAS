<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="js/write_question.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<script src="js/common.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<meta charset="UTF-8">
<title>QAS</title>
</head>
<body>
	<input type="hidden" id=isLogin value="${isLogin }" />
	<h1>질문 작성하기</h1>
	<form name="questionForm">
		<input type="text" name="title" id="title" maxlength="80" />
		<select name="category" id="category">
			<option value="">--선택--</option>
			<option value="edu">교육</option>
			<option value="game">게임</option>
			<option value="health">건강</option>
			<option value="trip">여행</option>
			<option value="shopping">쇼핑</option>
			<option value="life">생활</option>
		</select>
		<p><span id="title_count">0</span>/80</p>
		<textarea name="content" id="content" cols="30" rows="10" maxlength="2000"></textarea>
		<p><span id="content_count">0</span>/2000</p>
		<br />
		<input type="button" onclick="saveQ()" value="질문 저장" />
	</form>
</body>
</html>