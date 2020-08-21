<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./css/err.css">
<script src="./js/err.js" type="text/javascript"></script>
<meta charset="UTF-8">
<title>404</title>
</head>
<body>
    <div id="error">
    	<img src="./image/error.png">
    	<p id="errorTitle">죄송합니다. 현재 찾을 수 없는 페이지를 요청했습니다.</p>
    	<p id="errorContent">존재하지 않는 주소를 입력하셨거나,<br> 요청하신 페이지의 주소가 변경, 삭제되어 찾을 수 없습니다.</p>
    	<button id="main" onclick="back_main()">메인으로</button>
    	<button id="page" onclick="back_page()">이전 페이지</button>
    </div>
</body>
</html>