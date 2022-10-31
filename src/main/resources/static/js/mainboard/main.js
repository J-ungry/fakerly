const postList=document.getElementById("container");

async function readMainBoard() {	//메인게시판 불러오는 함수
	let url="/mainboard/main";
	let resp=await fetch(url);
	if(resp.status==200) {
		let textHtml=await resp.text();
		postList.innerHTML=textHtml;
	} else {
		alert("알 수 없는 오류");
	}
}

function updateForm() {
	const updateForm=document.getElementById("updateForm");
	if(updateForm.style.display=="none") {
		updateForm.style.display="block";
	} else {
		updateForm.style.display="none";
	}
}

