function isProfile(){
	let isUserId = $('#thisUserId').val();
	if(isUserId == null){
		location.href = "/test/noneProfile.jsp";
	}	
}

function requestQ(profileUserId){
	if($('#myId').val() == ""){
		if(confirm('로그인이 필요한 서비스입니다. 로그인하시겠습니까?') == true){
			location.href="/test/login.jsp";
		} else{
			return;
		}
	} else{
		location.href="/test/request_question.jsp?requestId=" + profileUserId;
	}
}


window.onload = ()=>{
	headerButton();
	isProfile();
}