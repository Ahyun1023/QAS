<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./css/err.css">
<script src="./js/err.js" type="text/javascript"></script>
<meta charset="UTF-8">
<title>500</title>
</head>
<body>
	<div id="error">
    	<img src="./image/error.png">
    	<p id="errorTitle">죄송합니다. 500</p>
    	<p id="errorContent">잠시후에 다시 시도해주세요.</p>
    	<button id="main" onclick="back_main()">메인으로</button>
    	<button id="page" onclick="back_page()">이전 페이지</button>
    </div>
</body>
</html>