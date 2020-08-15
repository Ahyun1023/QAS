	function signup(){
	let id = $('#id').val();
    let pw = $('#pw').val();
	let pw_check = $('#pw_check').val();
    let name = $('#name').val();
    let address = $('#address').val();
    let email = $('#email').val();
	let phone = $('#phone').val();
	let interests = $('#interests').val();
	
	if(id == ''){
		alert('아이디를 입력해주세요.');
	} else if(pw == '' || pw_check == ''){
		alert('비밀번호를 입력해주세요.');
	}else if(pw != pw_check){
		alert('비밀번호가 틀렸습니다. 다시 입력해주세요.');
	} else if(name == ''){
		alert('이름을 입력해주세요.');
	} else if(address == ''){
		alert('주소를 입력해주세요.');
	} else if(email == ''){
		alert('이메일을 입력해주세요.');
	} else if(phone == ''){
		alert('전화번호를 입력해주세요.');
	}else if(interests == ''){
		alert('관심분야를 설정해주세요.');
	} else{
	    $(document).ready(()=>{
        $.ajax({
            url: '/test/user/add.do',
            type: 'POST',
			//dataType: 'JSON',
            data: {
            	signup_id: id,
            	signup_pw: pw,
            	signup_name: name,
            	signup_address: address,
            	signup_email: email,
            	signup_phone: phone,
				signup_interests: interests
            },
            success:()=>{
				alert('회원가입 성공');
				location.href = "login.html";
            }
        })
    })	
	}
}