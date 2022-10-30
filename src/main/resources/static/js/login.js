const emailLoginInput = document.querySelector("#emailLoginInput");
const emailChkLogin = document.querySelector("#emailChkLogin");

const emailValue = new RegExp(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/);

emailLoginInput.onblur = function(e) {
	console.log("커서가 밖을 나갔습니다.");
	if (!emailValue.test(emailLoginInput.value)) {
		console.log("이메일 양식에 맞게 입력해주세요.");
		emailChkLogin.textContent = "이메일 양식에 맞게 입력해주세요.";
	} else {
		emailChkLogin.textContent = "";
	}
}