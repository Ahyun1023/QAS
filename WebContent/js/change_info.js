function change_info(){
	let pw = $('#pw').val();
	let pw_check = $('#pw_check').val();
	let name = $('#name').val();
	let address = $('#address').val();
	let email = $('#email').val();
	let phone = $('#phone').val();
	let interests = $('#interests').val();
	
	console.log(interests);
	
	if(pw == '' || pw_check == ''){
		alert('비밀번호를 입력해주세요.');
	} else if(pw != pw_check){
		alert('비밀번호가 맞지 않습니다. 다시 입력해주세요.');
	} else if(name == ''){
		alert('이름을 입력해주세요.');
	} else if(address == ''){
		alert('주소를 입력해주세요.');
	} else if(email == ''){
		alert('이메일을 입력해주세요.');
	} else if(phone == ''){
		alert('전화번호를 입력해주세요.');
	} else{
		    $(document).ready(()=>{
       			$.ajax({
					url: '/test/user/update.do',
					type: 'POST',
					data: {
						Cpw: pw,
						Cname: name,
						Caddress: address,
						Cemail: email,
						Cphone: phone,
						Cinterests: interests
					},
					success: ()=>{
						alert('정보 수정에 성공했습니다.');
						location.href = './mypage.jsp';
					}					
			})
		})
	}
}
