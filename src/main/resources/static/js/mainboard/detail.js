const replyInserForm=document.forms["replyInserForm"];

if(replyInserForm!=null){
	replyInserForm.addEventListener("submit",async(e)=>{
		e.preventDefault();
		let boardNo=replyInserForm.boardNo.value;
		let url=replyInserForm.action;
		let data=new FormData(replyInserForm); 
		//multipart/form-data일대 파라미터를 blob으로 처리
		let resp=await fetch(url,{body:data,method:"post"});
		
	});
}

async function readReplyUpdateForm(replyNo){
	console('asdasd')
	let url="/reply/update?replyNo="+replyNo;
	const replyLiNode=document.getElementById("replyLi"+replyNo);
	let resp=await fetch(url);
	if(resp.status==200){
		let textHtml=await resp.text();
		replyLiNode.innerHTML=textHtml;
	}else if(resp.status==400){
		alert("로그인한 유저만 수정 가능");
	}else if(resp.status==401){
		alert("글쓴 유저만 수정 가능");
	}else if(reps.status==500){
		alert("수정 폼 불러오기 실패(db 에러)");
	}
}


async function deleteReply(replyNo,boardNo){
	let url="/reply/delete?replyNo="+replyNo;
	let resp=await fetch(url);
	let msg="";
	if(resp.status==200){
		let checkStatus=await resp.json();
		switch(checkStatus.status){
			case -2: msg="작성자만 삭제 가능"; break;
			case -1: msg="로그인 시 삭제 가능"; break;
			case 0: msg="삭제 실패 (db 오류)"; break;
			case 1: 
				msg="삭제 성공"; 
				readReplyList(boardNo);
			break;			
		}

	}else{
		msg="서버 통신 장애";
	}
	alert(msg);
}


async function updateReply(formNode){
	let url="/reply/update";
	let boardNo=formNode.boardNo.value;
	let data=new FormData(formNode);
	let resp=await fetch(url,{body:data,method:"post"});
	let msg="";
	if(resp.status==200){
		let checkStatus=await resp.json();
		switch(checkStatus.status){
			case -2: msg="작성자만 수정 가능"; break;
			case -1: msg="로그인 시 수정 가능"; break;
			case 0: msg="수정 실패 (db 오류)"; break;
			case 1: 
				msg="수정 성공"; 
				readReplyList(boardNo);
			break;			
		}
		
	}else{
		msg="수정 통신 실패"
	}
	alert(msg);
}