function change_info(){
	let special_check = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
    let kor_check =  /^[A-Za-z0-9]+$/; 
    let blank_check = /[\s]/g;

	let userId = $('#userId').val();
	let pw = $('#pw').val();
	let pw_check = $('#pw_check').val();
	let name = $('#name').val();
	let email = $('#email').val();
	let emailForm = $('#emailForm').val();
	let interests = $('#interests').val();
	let introduce = $('#introduce').val();
	
	if(pw == '' || pw_check == ''){
		alert('비밀번호를 입력해주세요.');
	} else if(pw == '' || pw_check == ''){
		alert('비밀번호를 입력해주세요.');
	} else if(pw.length < 8 || pw.length > 16){
		alert('비밀번호는 8~16자의 영문과 숫자만 입력할 수 있습니다.');
	} else if(!kor_check.test(pw)){
		alert('비밀번호는 공백 없이 영문과 숫자만 작성할 수 있습니다.');
	} else if(pw != pw_check){
		alert('비밀번호가 틀렸습니다. 다시 입력해주세요.');
	} else if(name == ''){
		alert('이름을 입력해주세요.');
	}else if(name.length < 2 || name.length > 8 || blank_check.test(name) == true || kor_check.test(name) == true || special_check.test(name)){
		alert('이름은 2~8자의 공백 없이 한글만 입력할 수 있습니다.');
	} else if(email == ''){
		alert('이메일을 입력해주세요.');
	}else if(!kor_check.test(email)){
		alert('올바른 이메일을 입력해주세요.');
	} else if(emailForm == ''){
		alert('이메일 폼을 선택해주세요.');
	} else{
		$(document).ready(()=>{
       		$.ajax({
				url: '/test/user/update.do',
				type: 'POST',
				data: {
					Cpw: pw,
					Cname: name,
					Cemail: email,
					CemailForm: emailForm,
					Cinterests: interests,
					Cintroduce: introduce
				},
				success: ()=>{
					alert('정보 수정에 성공했습니다.');
					location.href = '/test/profile?userId=' + userId;
				}					
			})
		})
	}
}

function goBack(){
	history.back();
}

window.onload = ()=>{
	loginCheck();
}
