async function selecedKeyword(userNo){
	
	let url = "/items/listUserKeyword.do?userNo="+userNo;
	let resp = await fetch(url);
	if(resp.status == 200){
		let html = await resp.text();
		
    	document.getElementById("selecedKeyword").innerHTML =  html;
	}	
}

async function deleteUserKeyword(userNo, keywordNo){

	let url = "/items/deleteUserKeyword.do?userNo="+userNo+"&keywordNo="+keywordNo;
	let resp = await fetch(url);
	if(resp.status == 200){
		let json = await resp.json();
    	if(json.status == 1){
			selecedKeyword(userNo);
		}
	}
}

async function insertUserKeyword(idVal, userNo, keywordNo){

	let url = "/items/insertUserKeyword.do?userNo="+userNo+"&keywordNo="+keywordNo;
	let resp = await fetch(url);
	if(resp.status == 200){
		let json = await resp.json();
    	if(json.status == 1){
			getList(idVal, userNo);
			selecedKeyword(userNo);
		}
	}
}

async function getList(idVal, userNo){
	
	document.getElementById(idVal).innerHTML="";
	
	let url = "/items/keywordList.do?userNo="+userNo;
	let resp = await fetch(url);
	if(resp.status == 200){
		let keywords = await resp.json();
    	for(ind in keywords){
			document.getElementById(idVal).innerHTML+='<li class="list-group-item" style="height:50px;">'+keywords[ind].keywordName+'<button type="button" onclick="insertUserKeyword(\''+idVal+'\','+userNo+','+keywords[ind].keywordNo+')" class="btn btn-outline-danger" style="float:right;">추가</button></li>';
		}
	}
}

selecedKeyword(1);