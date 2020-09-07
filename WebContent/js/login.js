function login(){
	let id = $('#id').val();
	let pw = $('#pw').val();
	if(id == '' || pw == ''){
		alert('아이디와 비밀번호를 입력해주세요.');
	} else{
			$(document).ready(()=>{
			$.ajax({
				url: '/test/user/login.do',
				type: 'POST',
				data: {id: id, pw: pw},
				success: (result)=>{
					if(result.result == 'success'){
						location.href = '/test/main';
					} else{
						alert('아이디와 비밀번호를 다시 확인해주세요.');
					}
				}
			})
		})
	}
}

window.onload = ()=>{
	headerButton();
	alreadyLogin();
}