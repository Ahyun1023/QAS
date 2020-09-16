function zoomIn(event){
	event.target.style.transform = "scale(1.1)";
    event.target.style.zIndex = 1;
    event.target.style.transition = "all 0.5s";
}

function zoomOut(event){
	event.target.style.transform = "scale(1)";
    event.target.style.zIndex = 0;
    event.target.style.transition = "all 0.5s";
}

function locationQuestion(QId){
	location.href = '/test/question/read.do?qid=' + QId;
}

function locationUserProfile(UserId){
	location.href = '/test/profile?userId=' + UserId;
}

window.onload = ()=>{
	headerButton()
}