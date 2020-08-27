function isProfile(){
	let isUserId = $('#thisUserId').val();
	if(isUserId == null){
		location.href = "/test/noneProfile.jsp";
	}	
}

window.onload = ()=>{
	isProfile();
}