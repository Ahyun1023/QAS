let isIdExist = true;

function signup(){
	let special_check = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
    let kor_check =  /^[A-Za-z0-9]+$/; //한글이 있을 때 false, 없을 때 true (공백까지 검사함) 
    let blank_check = /[\s]/g; //공백이 있을 때 true, 없을 때 false

	let id = $('#id').val();
    let pw = $('#pw').val();
	let pw_check = $('#pw_check').val();
    let name = $('#name').val();
    let email = $('#email').val();
	let emailForm = $('#emailForm').val();
	let interests = $('#interests').val();
	let introduce = $('#introduce').val();

	if(id == ''){
		alert('아이디를 입력해주세요.');
	} else if(isIdExist == true){
		alert('사용할 수 없는 아이디입니다. 다른 아이디를 사용해주세요.');
	} else if(id.length < 4 || id.length > 8){
		alert('아이디는 4~8자의 영문과 숫자만 입력할 수 있습니다.');
	} else if(pw == '' || pw_check == ''){
		alert('비밀번호를 입력해주세요.');
	} else if(pw.length < 8 || pw.length > 16){
		alert('비밀번호는 8~16자의 영문과 숫자만 입력할 수 있습니다.');
	} else if(!kor_check.test(id) || !kor_check.test(pw)){
		alert('아이디나 비밀번호는 공백 없이 영문과 숫자만 작성할 수 있습니다.');
	} else if(pw != pw_check){
		alert('비밀번호가 틀렸습니다. 다시 입력해주세요.');
	} else if(name == ''){
		alert('이름을 입력해주세요.');
	} else if(name.length < 2 || name.length > 8 || blank_check.test(name) == true || kor_check.test(name) == true || special_check.test(name)){
		alert('이름은 2~8자의 공백 없이 한글만 입력할 수 있습니다.');
	} else if(email == ''){
		alert('이메일을 입력해주세요.');
	} else if(!kor_check.test(email)){
		alert('올바른 이메일을 입력해주세요.');
	} else if($("input:checkbox[id='termsCheck']").is(':checked') == false){
		alert('약관을 읽고 동의해주세요.');
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
            		signup_email: email,
					signup_emailForm: emailForm,
					signup_interests: interests,
					signup_introduce: introduce
            	},
            	success:()=>{
					alert('회원가입 성공');
					location.href = "login.jsp";
            	}
        	})
    	})	
	}
}

function idExistCheck(){
	let kor_check =  /^[A-Za-z0-9]+$/;
	$('#id').change(()=>{
		$(document).ready(()=>{
			$.ajax({
				url:'/test/user/idCheck.do',
				type: 'POST',
				data:{
					checkId: $('#id').val()
				},
				success:(isTrue)=>{
					if(isTrue.isTrue == true){
						isIdExist = true;
						$('#isIdExist').css('color', 'red');
						$('#isIdExist').text('중복된 아이디입니다. 다른 아이디를 사용해주세요.');
					} else if(isTrue.isTrue == false){
						if(!kor_check.test($('#id').val())){
							$('#isIdExist').css('color', 'red');
							$('#isIdExist').text('사용할 수 없는 아이디입니다.');
						} else{
							isIdExist = false;
							$('#isIdExist').css('color', 'green');
							$('#isIdExist').text('사용할 수 있는 아이디입니다.');
						}
					}
				}
			})
		})
	})
}

window.onload = ()=>{
	headerButton();
	alreadyLogin();
	idExistCheck();
	pwCheck();
}