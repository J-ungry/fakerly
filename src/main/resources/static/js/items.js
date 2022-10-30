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
			getKeywordList(idVal, userNo);
			selecedKeyword(userNo);
		}
	}
}

async function getKeywordList(idVal, userNo){
	
	document.getElementById(idVal).innerHTML="";
	
	let url = "/items/"+idVal+".do?userNo="+userNo;
	let resp = await fetch(url);
	if(resp.status == 200){
		let items = await resp.json();
    	for(item in items){
			let keys = Object.keys(items[item]);
			document.getElementById(idVal).innerHTML+='<li class="list-group-item" style="height:50px;">'+items[item][keys[1]]+'<button type="button" onclick="insertUserKeyword(\''+idVal+'\','+userNo+','+items[item][keys[0]]+')" class="btn btn-outline-danger" style="float:right;">추가</button></li>';
		}
	}
}

async function selecedSkill(userNo){
	
	let url = "/items/listUserSkill.do?userNo="+userNo;
	let resp = await fetch(url);
	if(resp.status == 200){
		let html = await resp.text();
		
    	document.getElementById("selecedSkill").innerHTML =  html;
	}	
}

async function deleteUserSkill(userNo, skillNo){

	let url = "/items/deleteUserSkill.do?userNo="+userNo+"&skillNo="+skillNo;
	let resp = await fetch(url);
	if(resp.status == 200){
		let json = await resp.json();
    	if(json.status == 1){
			selecedSkill(userNo);
		}
	}
}

async function insertUserSkill(idVal, userNo, skillNo){

	let url = "/items/insertUserSkill.do?userNo="+userNo+"&skillNo="+skillNo;
	let resp = await fetch(url);
	if(resp.status == 200){
		let json = await resp.json();
    	if(json.status == 1){
			getSkillList(idVal, userNo);
			selecedSkill(userNo);
		}
	}
}

async function getSkillList(idVal, userNo){
	
	document.getElementById(idVal).innerHTML="";
	
	let url = "/items/"+idVal+".do?userNo="+userNo;
	let resp = await fetch(url);
	if(resp.status == 200){
		let items = await resp.json();
    	for(item in items){
			let keys = Object.keys(items[item]);
			document.getElementById(idVal).innerHTML+='<li class="list-group-item" style="height:50px;">'+items[item][keys[1]]+'<button type="button" onclick="insertUserSkill(\''+idVal+'\','+userNo+','+items[item][keys[0]]+')" class="btn btn-outline-danger" style="float:right;">추가</button></li>';
		}
	}
}

async function selecedLicense(userNo){
	
	let url = "/items/listUserLicense.do?userNo="+userNo;
	let resp = await fetch(url);
	if(resp.status == 200){
		let html = await resp.text();
		
    	document.getElementById("selecedLicense").innerHTML =  html;
	}	
}

async function deleteUserLicense(userNo, licenseNo){

	let url = "/items/deleteUserLicense.do?userNo="+userNo+"&licenseNo="+licenseNo;
	let resp = await fetch(url);
	if(resp.status == 200){
		let json = await resp.json();
    	if(json.status == 1){
			selecedLicense(userNo);
		}
	}
}

async function insertUserLicense(idVal, userNo, licenseNo){

	let url = "/items/insertUserLicense.do?userNo="+userNo+"&licenseNo="+licenseNo;
	let resp = await fetch(url);
	if(resp.status == 200){
		let json = await resp.json();
    	if(json.status == 1){
			getLicenseList(idVal, userNo);
			selecedLicense(userNo);
		}
	}
}

async function getLicenseList(idVal, userNo){
	
	document.getElementById(idVal).innerHTML="";
	
	let url = "/items/"+idVal+".do?userNo="+userNo;
	let resp = await fetch(url);
	if(resp.status == 200){
		let items = await resp.json();
    	for(item in items){
			let keys = Object.keys(items[item]);
			document.getElementById(idVal).innerHTML+='<li class="list-group-item" style="height:50px;">'+items[item][keys[1]]+'<button type="button" onclick="insertUserLicense(\''+idVal+'\','+userNo+','+items[item][keys[0]]+')" class="btn btn-outline-danger" style="float:right;">추가</button></li>';
		}
	}
}

selecedKeyword(1);
selecedSkill(1);
selecedLicense(1);