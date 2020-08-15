window.onload = function(){
	let islogin = $('#isLogin').val();
	if(islogin != "true"){
		alert('로그인되어 있지 않습니다.');
		location.href = "./login.html";
	}
}