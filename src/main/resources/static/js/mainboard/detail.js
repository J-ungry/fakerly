const replyInserForm=document.forms["replyInserForm"];





function readReplyUpdateForm(reply_no) {
	const replyNode=document.getElementById("reply_no"+reply_no);
	const replyupdate=document.getElementById("reply_update"+reply_no);

	if(replyupdate.style.display=="none"){
		console.log('댓글 수정폼 나타나라 얍')
		replyupdate.style.display="block";
		replyNode.style.display="none";
		
	}else{
		console.log('댓글 수정폼 사라져라 얍')
		replyNode.style.display="block";
		replyupdate.style.display="none";
	}

	
	
	
	
	
}

function readRereplyUpdateForm(rereply_no){
	const rereplyNode=document.getElementById("rereply_no"+rereply_no)
	const rereplyupdate=document.getElementById("rereply_update"+rereply_no)
	
	if(rereplyupdate.style.display=="none"){
		console.log('대댓글 수정폼 나타나라 얍')
		rereplyNode.style.display="none";
		rereplyupdate.style.display="block";
	}else{
		console.log('대댓글 수정폼 사라져라 얍')
		rereplyNode.style.display="block";
		rereplyupdate.style.display="none";
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