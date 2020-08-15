function signout(){
	let id = $('#signout_id').val();
	let pw = $('#signout_pw').val();
	let pw_check = $('#signout_pwcheck').val();
		
		if(pw != pw_check){
			alert('비밀번호가 다릅니다. 다시 입력해주세요.');
		} else {
			$(document).ready(()=>{
        	$.ajax({
            	url: '/test/user/delete.do',
            	type: 'POST',
				//dataType: 'JSON',
            	data: {
            		signout_id: id,
            		signout_pw: pw
            	},
            	success:()=>{
					alert('회원탈퇴 성공');
					location.href = "./main";	
            	}
        	})
    	})	
	}
}