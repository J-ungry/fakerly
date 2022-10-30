const searchMainBoardForm=document.forms["searchMainBoard"];
const postList=document.getElementById("postList");

if(searchMainBoardForm!=null) {	//검색 내용이 있을 경우 실행
	searchMainBoardForm.addEventListener("submit",async (e)=>{
		e.preventDefault();	//검색내용을 전송하는 동작 중단
		let searching=searchMainBoardForm.searching.value;
		let searchConcept=searchMainBoardForm.searchConcept.value;
		let url=searchMainBoardForm.action;
		let data=new FormData(searchMainBoardForm);
		let resp=await fetch(url,{body:data, method:"post"});
		if(resp.status==200) {
			let json=await resp.json();
			let msg="";
			switch(json.stauts) {
				case 0:
					msg="검색 결과가 없습니다";
					break;
				case 1:
					searchResult(searchConcept,searching);
					break;
				default:
					msg="알 수 없는 오류입니다.";
					break;
			}
			alert(msg);
		}
		
	})
	
}
	
async function searchResult(searchConcept,searching) {
	let url="/mainboard/"+searchConcept+"/searchList?searching="+searching;
	let rsep=await fetch(url);
	if(resp.status==200) {
		let textHtml=await resp.text();
		postList.innerHTML=textHtml;
	} else {
		alert("검색 결과 불러오기 실패");
	}
}
 
	
