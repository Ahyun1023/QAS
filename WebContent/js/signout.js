function signout(){
	let id = $('#id').val();
	let pw = $('#pw').val();
	let pw_check = $('#pw_check').val();
		
		if(id == '' || pw == '' || pw_check == ''){
			alert('빈 칸이 있습니다.');
		} else if(pw != pw_check){
			alert('비밀번호가 다릅니다. 다시 입력해주세요.');
		}else if($("input:checkbox[id='termsCheck']").is(':checked') == false){
			alert('안내 사항을 읽고 동의해주세요.');
		} else {
			if(confirm('회원탈퇴를 하시겠습니까?') == true){
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
		} else{
			return;
		}
	}
}

function locMain(){
	location.href="/test/main";
}

window.onload = ()=>{
	loginCheck();
	headerButton();
}