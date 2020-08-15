function saveQ() {
	let save_title = $('#title').val();
	let save_category = $('#category').val();
	let save_content = $('#content').val();
	
	if(save_title == ''){
		alert('제목을 입력해주세요.');
	} else if(save_content == ''){
		alert('내용을 입력해주세요.');
	} else if(save_category == ''){
		alert('질문의 카테고리를 입력해주세요.');
	} else{
		if(confirm("답변 작성 이후 수정과 삭제를 할 수 없습니다. 질문을 등록하시겠습니까?") == true){
			$(document).ready(() => {
			$.ajax({
				url: '/test/question/add.do',
				type: 'POST',
				//dataType: 'JSON',
				data: {
					save_title: save_title,
					save_category: save_category,
					save_content: save_content
				},
				success: () => {
					alert('질문이 성공적으로 저장되었습니다.');
					window.location.replace( "/test/main");
				},
					error: (request, status, error) => {
						alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
					}
				})
			})
		} else{
			return;
		}
	}
}