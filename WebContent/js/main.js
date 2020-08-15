function locLogin(){
	location.href = "/test/login.html";
}
function locSignup(){
	location.href = "/test/signup.html";
}
function locQuestion(){
	if($('#isLogin').val() != "true"){
		if(confirm("로그인이 필요한 서비스입니다. 로그인하시겠습니까?") == true){
			location.href='/test/login.html';
		} else{
			return;
		}
	}else{
		location.href = "/test/write_question.jsp";
	}
}

function locMypage(){
	if($('#isLogin').val() != "true"){
		if(confirm("로그인이 필요한 서비스입니다. 로그인하시겠습니까?") == true){
			location.href='/test/login.html';
		} else{
			return;
		}
	} else{
		location.href = "/test/mypage.jsp";
	}
}

function logout(){
	$(document).ready(()=>{
		$.ajax({
			url:"/test/user/logout.do",
			type:"POST",
			success: ()=>{
				alert('로그아웃 되었습니다.');
				location.href = "/test/main";
			}
		})
	})
}

window.onload = ()=>{
	let islogin = $('#isLogin').val();
	
	if(islogin == "true"){
		$('#signupButton').remove();
		$('#loginButton').remove();
	} else{
		$('#logoutButton').remove();
	}
}
