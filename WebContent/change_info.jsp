<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String interests = (String)session.getAttribute("sessionInterests");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="js/change_info.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<script src="js/common.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<title>QAS</title>
</head>
<body>
	<input type="hidden" id="isLogin" value=${isLogin } />
	<h1>회원 정보 수정</h1>
	<form name="signupForm">
		비밀번호: <input type="password" id="pw" /><br>
		비밀번호 확인: <input type="password" id="pw_check" /><br>
		이름: <input type="text" id="name" value="${sessionName}"/><br>
		주소: <input type="text" id="address" value="${sessionAddress}" /><br>
		이메일: <input type="text" id="email" value="${sessionEmail}" /><br>
		전화번호: <input type="text" id="phone" value="${sessionPhone}" /><br>
		관심 분야: <select name="interests" id="interests">
			<option value="edu" <%if(interests.equals("edu")){%> selected<%}%>>교육</option>
			<option value="game" <%if(interests.equals("game")){%> selected<%}%>>게임</option>
			<option value="health" <%if(interests.equals("health")){%> selected<%}%>>건강</option>
			<option value="trip" <%if(interests.equals("trip")){%> selected<%}%>>여행</option>
			<option value="shopping" <%if(interests.equals("shopping")){%> selected<%}%>>쇼핑</option>
			<option value="life" <%if(interests.equals("life")){%> selected<%}%>>생활</option>
		</select><br>
		<input type="button" value="회원 정보 변경" onclick="change_info()"/>
		<input type="reset" value="다시 입력" />
		<a href="./main.jsp">메인 페이지로 이동</a>
	</form>
</body>
</html>