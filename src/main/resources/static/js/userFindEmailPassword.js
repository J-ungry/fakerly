
console.log("findEmailPassword.js");

const phoneE = document.querySelector("#phoneE");
const phoneP = document.querySelector("#phoneP");
const phoneNbChkE = document.querySelector("#phoneNbChkE");
const phoneNbChkP = document.querySelector("#phoneNbChkP");

phoneE.onblur = function(e) {
	if (phoneE.value.length != 11) {
		console.log("전화번호 11자리를 입력해주세요.");
		phoneNbChkE.textContent = "전화번호 11자리를 입력해주세요.";
	} else {
		phoneNbChkE.textContent = "";
	}
}

phoneP.onblur = function(e) {
	if (phoneP.value.length != 11) {
		console.log("전화번호 11자리를 입력해주세요.");
		phoneNbChkP.textContent = "전화번호 11자리를 입력해주세요.";
	} else {
		phoneNbChkP.textContent = "";
	}
}