function locLogin(){
	location.href = "/test/login.jsp";
}
function locSignup(){
	location.href = "/test/signup.jsp";
}
function locQuestion(){
	if($('#isLogin').val() != "true"){
		if(confirm("로그인이 필요한 서비스입니다. 로그인하시겠습니까?") == true){
			location.href='/test/login.jsp';
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
			location.href='/test/login.jsp';
		} else{
			return;
		}
	} else{
		location.href = "/test/mypage";
	}
}

function logout(){
	if($('#isLogin').val() != "true"){
		alert('이미 로그인되어 있지 않습니다.');
		location.href = "/test/main";
	}
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
	if($('#isLogin').val() == "true"){
		$('#signupButton').remove();
		$('#loginButton').remove();
	} else{
		$('#logoutButton').remove();
	}
}
