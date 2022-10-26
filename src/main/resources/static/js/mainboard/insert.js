function goBack() {	//이전 페이지로 돌아가는 함수
	window.history.back();
}

function showLink() {
	const linkInput=document.getElementById("linkInputContainer");
	if(linkInput.style.display=="none") {
		linkInput.style.display="block";
	} else {
		linkInput.style.display="none";
	}
}

function showImg() {
	const imgInput=document.getElementById("fileInputContainer");
	if(imgInput.style.display=="none") {
		imgInput.style.display="block";
	} else {
			imgInput.style.display="none";
	}
	
}