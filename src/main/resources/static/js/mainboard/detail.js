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


