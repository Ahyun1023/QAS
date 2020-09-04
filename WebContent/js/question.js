window.onload = ()=>{
	headerButton();
	let questionId = $('#questionId').val();
	if(questionId == undefined){
		location.href = "/test/noneQuestion.jsp";
		}
	}

function writeAns(){
	let isLogin = $('#isLogin').val();
	let qId = $('#answerQid').val();
	let category = $('#questionCategory').val();
	let title = $('#answerTitle').val();
	let content = $('#answerContent').val();
	let isUserid = $('#isUserid').val();
	
	if(isLogin != "true"){
		if(confirm("로그인이 필요한 서비스입니다. 로그인하시겠습니까?") == true){
			location.href='/test/login.jsp';
		} else{
			return;
		}
		
	} else if(isUserid != undefined){
		alert('이미 이 질문에 답변을 하셨습니다.');
		return;
	} else if(title == '' || title == null){
		alert('답변의 제목을 작성해주세요.');
		return;
	} else if(content == '' || content == null){
		alert('답변의 내용을 작성해주세요.');
		return;
	} else{		
		if(confirm('답변이 채택되면 답변을 수정할 수 없습니다.\n답변을 등록하시겠습니까?') != true){
			return;
		} else{
			$(document).ready(()=>{
				$.ajax({
					url: "/test/answer/add.do",
					type: "POST",
					data: {
						answerQid: qId,
						answerCategory: category,
						answerTitle: title,
						answerContent: content
					},
					success: ()=>{
						alert('정상적으로 답변이 작성되었습니다.');
						location.reload();
					}
				})
			})
		}

	}
}

function deleteAnswer(Aid){
	if(confirm("답변을 삭제하시겠습니까?") == true){ 
		$(document).ready(()=>{
			$.ajax({
				url: "/test/answer/delete.do",
				type: "post",
				data: {deleteAid: Aid},
				success: ()=>{
					alert('답변이 삭제되었습니다.');
					location.reload();
				}
			})
		})
 	} else{
     return false;
	}
}

function modifiedAnswer(Aid){
	let AtitleHtml = document.getElementById("Atitle" + Aid);
	let AcontentHtml = document.getElementById("Acontent" + Aid);
	let Atitle = $('#Atitle' + Aid).text();
	let Acontent = $('#Acontent' + Aid).text();
	
	document.getElementById("AmodifiedButton").setAttribute("onClick", "updateAnswer(" + Aid + ")");

	AtitleHtml.innerHTML = "<input type='text' id='update_Title' value='" + Atitle +"'>";
	AcontentHtml.innerHTML = "<textarea id='update_Content' cols='30' rows='10'>" + Acontent + "</textarea>";
}

function updateAnswer(Aid){
	let updateTitle = $('#update_Title').val();
	let updateContent = $('#update_Content').val();
	
	$(document).ready(()=>{
		$.ajax({
			url: "/test/answer/update.do",
			type: "post",
			data: {
				updateTitle: updateTitle,
				updateContent: updateContent,
				updateAnswerId: Aid
			},
			success: ()=>{
				alert('답변이 성공적으로 수정되었습니다.');
				location.reload();
			}
		})
	})
}

function deleteQuestion(Qid){
	let isAnswer = $('#isAnswer').val();
	if(isAnswer == "false"){
		if(confirm("질문을 삭제하시겠습니까?") == true){
			$(document).ready(()=>{
				$.ajax({
					url: "/test/question/delete.do",
					type: "post",
					data: {deleteQid: Qid},
					success: ()=>{
						alert('질문이 삭제되었습니다.');
						location.href="/test/main";
					}
				})
			})
 		} else{
     	return false;
		}
	} else{
		alert('이미 답변이 작성된 질문은 삭제할 수 없습니다.');
		return;
	}
}

function modifiedQuestion(Qid){
	let isAnswer = $('#isAnswer').val();
	if(isAnswer == "false"){
		let QtitleHtml = document.getElementById("Qtitle" + Qid);
		let QcontentHtml = document.getElementById("Qcontent" + Qid);
		let Qtitle = $('#Qtitle' + Qid).text();
		let Qcontent = $('#Qcontent' + Qid).text();
	
		document.getElementById("QmodifiedButton").setAttribute("onClick", "updateQuestion()");
	
		QtitleHtml.innerHTML = "<input type='text' id='update_Title' value='" + Qtitle +"'>";
		QcontentHtml.innerHTML = "<textarea id='update_Content' cols='30' rows='10'>" + Qcontent + "</textarea>";
	} else{
		alert('이미 답변이 작성된 질문은 수정할 수 없습니다.');
	}

}

function updateQuestion(){
	let updateTitle = $('#update_Title').val();
	let updateContent = $('#update_Content').val();
	let updateQuestionId = $('#questionId').val();
	
	$(document).ready(()=>{
		$.ajax({
			url: "/test/question/update.do",
			type: "post",
			data: {
				updateTitle: updateTitle,
				updateContent: updateContent,
				updateQuestionId: updateQuestionId
			},
			success: ()=>{
				alert('질문이 성공적으로 수정되었습니다.');
				location.reload();
			}
		})
	})
}

function selectionAnswer(Aid){
	let qId = $('#questionId').val();
	if(confirm("이 답변을 채택하시겠습니까?") != true){
		return;
	} else{
		$(document).ready(()=>{
			$.ajax({
				url: "/test/question/select.do",
				type: "post",
				data: {
					qId: qId,
					selectAuserId: Aid,
					request_user: $('#request_user').val()
				},
				success: ()=>{
					alert('해당 답변을 성공적으로 채택했습니다.');
					location.reload();
				}
			})
		})	
	}
}