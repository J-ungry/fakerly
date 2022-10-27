async function insertUserKeyword(window, userNo, keywordNo){
	console.log("dd");
	let url = "/items/insertUserKeyword.do?userNo="+userNo+"&keywordNo="+keywordNo;
	let resp = await fetch(url);
	if(resp.status == 200){
		let json = await resp.json();
    	if(json.status == 1){
			getList(window, userNo);
		}
	}
}

async function getList(idVal, userNo){
	
	document.getElementById(idVal).innerHTML=""
	
	let url = "/items/keywordList.do?userNo="+userNo;
	let resp = await fetch(url);
	if(resp.status == 200){
		let keywords = await resp.json();
    	for(ind in keywords){
			document.getElementById(idVal).innerHTML+='<li class="list-group-item">'+keywords[ind].keywordName+'<button type="button" onclick="insertUserKeyword(\''+idVal+'\','+userNo+','+keywords[ind].keywordNo+')" class="btn btn-outline-danger">추가</button></li>';
		}
	}
}