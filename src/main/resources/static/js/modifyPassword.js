const newPasswordInput = document.querySelector("#newPassword");
const chkNewPasswordInput = document.querySelector("#chkNewPassword");

const newPasswordValidChk = document.querySelector("#newPasswordValidChk");
const chkNewPasswordValidChk = document.querySelector("#chkNewPasswordValidChk");

//

newPasswordInput.onblur = function(e) {
	let pwLen = newPasswordInput.value.length;
	let bigFlag = false;
	let smallFlag = false;
	let numFlag = false;
	for (let x = 0; x < pwLen; x++) {
		if (newPasswordInput.value.charAt(x) >= 'A' && newPasswordInput.value.charAt(x) <= 'Z') {
			bigFlag = true
		};
		if (newPasswordInput.value.charAt(x) >= 'a' && newPasswordInput.value.charAt(x) <= 'z') {
			smallFlag = true
		};
		if (newPasswordInput.value.charAt(x) >= '0' && newPasswordInput.value.charAt(x) <= '1') {
			numFlag = true
		};
	}

	if (pwLen >= 8 && pwLen <= 15) {
		if (bigFlag == true) {
			if (smallFlag == true) {
				if (numFlag == true) {
					newPasswordValidChk.textContent = "";
				} else {
					newPasswordValidChk.textContent = "숫자를 포함하세요";
				}
			} else {
				newPasswordValidChk.textContent = "소문자를 포함하세요";
			}
		} else {
			newPasswordValidChk.textContent = "대문자를 포함하세요";
		}
	} else {
		newPasswordValidChk.textContent = "8~15글자를 입력하세요";
	}
}

//

chkNewPasswordInput.onblur = function(e) {
	let pwLen = chkNewPasswordInput.value.length;
	let bigFlag = false;
	let smallFlag = false;
	let numFlag = false;
	for (let x = 0; x < pwLen; x++) {
		if (chkNewPasswordInput.value.charAt(x) >= 'A' && chkNewPasswordInput.value.charAt(x) <= 'Z') {
			bigFlag = true
		};
		if (chkNewPasswordInput.value.charAt(x) >= 'a' && chkNewPasswordInput.value.charAt(x) <= 'z') {
			smallFlag = true
		};
		if (chkNewPasswordInput.value.charAt(x) >= '0' && chkNewPasswordInput.value.charAt(x) <= '1') {
			numFlag = true
		};
	}

	if (pwLen >= 8 && pwLen <= 15) {
		if (bigFlag == true) {
			if (smallFlag == true) {
				if (numFlag == true) {
					if (newPasswordInput.value === chkNewPasswordInput.value) {
						chkNewPasswordValidChk.textContent = "";
					} else {
						chkNewPasswordValidChk.textContent = "새 비밀번호가 일치하지 않습니다.";
					}
				} else {
					chkNewPasswordValidChk.textContent = "숫자를 포함하세요";
				}
			} else {
				chkNewPasswordValidChk.textContent = "소문자를 포함하세요";
			}
		} else {
			chkNewPasswordValidChk.textContent = "대문자를 포함하세요";
		}
	} else {
		chkNewPasswordValidChk.textContent = "8~15글자를 입력하세요";
	}
}