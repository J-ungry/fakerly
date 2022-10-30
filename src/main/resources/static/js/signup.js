//이메일 유효성 검사
signupForm.email.addEventListener("input",checkEmail)
function checkEmail(){
	
	signupForm.email.classList.remove("is-invalid");
	signupForm.email.classList.remove("is-valid");
	
	let flag = false;
	let emailForms = ["naver.com","gmail.com","hanmail.net"];
	let emailSplit = signupForm.email.value.split('@');
	
	if(emailSplit.length == 2 && emailSplit[0] != ""){
		for(let x = 0;x<emailForms.length; x++){
			if(emailForms[x] === emailSplit[1]){
				signupForm.email.classList.add("is-valid");
				flag = true;
				break;
			}
		}
	}
	if(flag==false){
		signupForm.email.classList.add("is-invalid");
		emailInvalid.innerText = "이메일 형식을 확인하세요";
	}
}
//비밀번호 유효성 검사
signupForm.pw.addEventListener("input",checkPw)
function checkPw(){
	
	signupForm.pw.classList.remove("is-invalid");
	signupForm.pw.classList.remove("is-valid");
	
	let pwLen = signupForm.pw.value.length;
	let bigFlag = false;
	let smallFlag = false;
	let numFlag = false;
	for(let x = 0;x<pwLen; x++){
		if(signupForm.pw.value.charAt(x)>='A' && signupForm.pw.value.charAt(x)<='Z') bigFlag = true;
		if(signupForm.pw.value.charAt(x)>='a' && signupForm.pw.value.charAt(x)<='z') smallFlag = true;
		if(signupForm.pw.value.charAt(x)>='0' && signupForm.pw.value.charAt(x)<='1') numFlag = true;
	}
	
	if(pwLen >= 8 && pwLen <= 15){
		if(bigFlag == true){		
			if(smallFlag == true){			
				if(numFlag == true){				
					signupForm.pw.classList.add("is-valid");
				}else{
					signupForm.pw.classList.add("is-invalid");
					pwInvalid.innerText = "숫자를 포함하세요";
				}
			}else{
				signupForm.pw.classList.add("is-invalid");
				pwInvalid.innerText = "소문자를 포함하세요";
			}
		}else{
			signupForm.pw.classList.add("is-invalid");
			pwInvalid.innerText = "대문자를 포함하세요";
		}
	}else{
		signupForm.pw.classList.add("is-invalid");
		pwInvalid.innerText = "8~15글자를 입력하세요";
	}	
}
//비밀번호 확인 유효성 검사
signupForm.pwCheck.addEventListener("input",checkPwCheck)
function checkPwCheck(){
	
	signupForm.pwCheck.classList.remove("is-invalid");
	signupForm.pwCheck.classList.remove("is-valid");
	
	if(signupForm.pw.value === signupForm.pwCheck.value){
		signupForm.pwCheck.classList.add("is-valid");
	}else{
		signupForm.pwCheck.classList.add("is-invalid");
		pwCheckInvalid.innerText = "비밀번호가 불일치합니다";
	}	
}
//닉네임 유효성 검사
signupForm.nickname.addEventListener("input",checkNickname)
async function checkNickname(){
	
	signupForm.nickname.classList.remove("is-invalid");
	signupForm.nickname.classList.remove("is-valid");
	
	let nickname = signupForm.nickname.value;
	let url="/user/checkNickname.do?nickname="+nickname;	
	
	let resp = await fetch(url);
	if(resp.status == 200){
		let json = await resp.json();
		if(json){
			signupForm.nickname.classList.add("is-invalid");
			nicknameInvalid.innerText = "사용중인 닉네임입니다";
		}else{
			signupForm.nickname.classList.add("is-valid");
		}
	}
}
//전화번호 유효성 검사
signupForm.phone.addEventListener("input",checkPhone)
function checkPhone(){
	
	signupForm.phone.classList.remove("is-invalid");
	signupForm.phone.classList.remove("is-valid");
	
	let phone = signupForm.phone.value;
	
	signupForm.phone.value = phone.replace(/[^0-9]/g, '');
	if(phone.length == 11 && phone.substr(0,3) === "010"){
		signupForm.phone.classList.add("is-valid");
	}else{
		signupForm.phone.classList.add("is-invalid");
		phoneInvalid.innerText = "전화번호를 확인해주세요";
	}
}
//제출 전 확인
signupForm.addEventListener("change",checksignupForm)
function checksignupForm(){
	
	if(signupForm.email.classList.contains("is-valid")){
		if(signupForm.pw.classList.contains("is-valid")){
			if(signupForm.pwCheck.classList.contains("is-valid")){	
				if(signupForm.user_name.value !== ""){		
					if(signupForm.nickname.classList.contains("is-valid")){	
						if(signupForm.birth.value !== ""){
							if(signupForm.phone.classList.contains("is-valid")){					
								btns[6].removeAttribute("disabled",false);
								btns[6].style.backgroundColor = "#ed6653";
							}else{		
								btns[6].setAttribute("disabled",true);
								btns[6].style.backgroundColor = "#ddd";
							}
						}	else{		
								btns[6].setAttribute("disabled",true);
								btns[6].style.backgroundColor = "#ddd";
							}		
					}else{		
						btns[6].setAttribute("disabled",true);
						btns[6].style.backgroundColor = "#ddd";
					}	
				}else{		
					btns[6].setAttribute("disabled",true);
					btns[6].style.backgroundColor = "#ddd";
				}
			}else{		
				btns[6].setAttribute("disabled",true);
				btns[6].style.backgroundColor = "#ddd";
			}
		}else{		
			btns[6].setAttribute("disabled",true);
			btns[6].style.backgroundColor = "#ddd";
		}
	}else{		
		btns[6].setAttribute("disabled",true);
		btns[6].style.backgroundColor = "#ddd";
	}
}


let divs = [document.querySelector("#accountInfo"),document.querySelector("#userInfo1"),document.querySelector("#userInfo2"),document.querySelector("#userInfo3")];
let h1s = [document.querySelector("#accountInfo>h1"),document.querySelector("#userInfo1>h1"),document.querySelector("#userInfo2>h1"),document.querySelector("#userInfo3>h1")];
let forms = [document.querySelector("#accountInfo #accountInfoForm"),document.querySelector("#userInfo1 #userInfo1Form"),document.querySelector("#userInfo2 #userInfo2Form"),document.querySelector("#userInfo3 #userInfo3Form")];
let btns = [document.querySelector("#accountInfo .rBtn"),document.querySelector("#userInfo1 .lBtn"),document.querySelector("#userInfo1 .rBtn"),document.querySelector("#userInfo2 .lBtn"),document.querySelector("#userInfo2 .rBtn"),document.querySelector("#userInfo3 .lBtn"),document.querySelector("#userInfo3 .rBtn")];

btns[0].onclick = function(){
	
	divs.forEach(div =>div.style.width = "10%");
	divs[1].style.width = "68%";

	h1s.forEach(h1 =>h1.style.visibility = "visible");
	h1s[1].style.visibility = "hidden";

	forms.forEach(form =>form.style.visibility = "hidden");
	forms[1].style.visibility = "visible";

	btns.forEach(btn => btn.style.visibility = "hidden");
    btns[1].style.visibility = "visible";
    btns[2].style.visibility = "visible";
}

btns[1].onclick = function(){
	
	divs.forEach(div =>div.style.width = "10%");
	divs[0].style.width = "68%";

	h1s.forEach(h1 =>h1.style.visibility = "visible");
	h1s[0].style.visibility = "hidden";

	forms.forEach(form =>form.style.visibility = "hidden");
	forms[0].style.visibility = "visible";

	btns.forEach(btn => btn.style.visibility = "hidden");
    btns[0].style.visibility = "visible";
}

btns[2].onclick = function(){
	
	divs.forEach(div =>div.style.width = "10%");
	divs[2].style.width = "68%";

	h1s.forEach(h1 =>h1.style.visibility = "visible");
	h1s[2].style.visibility = "hidden";

	forms.forEach(form =>form.style.visibility = "hidden");
	forms[2].style.visibility = "visible";

	btns.forEach(btn => btn.style.visibility = "hidden");
    btns[3].style.visibility = "visible";
    btns[4].style.visibility = "visible";
}

btns[3].onclick = function(){
	
	divs.forEach(div =>div.style.width = "10%");
	divs[1].style.width = "68%";

	h1s.forEach(h1 =>h1.style.visibility = "visible");
	h1s[1].style.visibility = "hidden";

	forms.forEach(form =>form.style.visibility = "hidden");
	forms[1].style.visibility = "visible";

	btns.forEach(btn => btn.style.visibility = "hidden");
    btns[1].style.visibility = "visible";
    btns[2].style.visibility = "visible";
}
btns[4].onclick = function(){
	
	divs.forEach(div =>div.style.width = "10%");
	divs[3].style.width = "68%";

	h1s.forEach(h1 =>h1.style.visibility = "visible");
	h1s[3].style.visibility = "hidden";

	forms.forEach(form =>form.style.visibility = "hidden");
	forms[3].style.visibility = "visible";

	btns.forEach(btn => btn.style.visibility = "hidden");
    btns[5].style.visibility = "visible";
    btns[6].style.visibility = "visible";
}
btns[5].onclick = function(){
	
	divs.forEach(div =>div.style.width = "10%");
	divs[2].style.width = "68%";

	h1s.forEach(h1 =>h1.style.visibility = "visible");
	h1s[2].style.visibility = "hidden";

	forms.forEach(form =>form.style.visibility = "hidden");
	forms[2].style.visibility = "visible";

	btns.forEach(btn => btn.style.visibility = "hidden");
    btns[3].style.visibility = "visible";
    btns[4].style.visibility = "visible";
}

function temp(){
	divs.forEach(div =>div.style.width = "10%");
	divs[3].style.width = "68%";

	h1s.forEach(h1 =>h1.style.visibility = "visible");
	h1s[3].style.visibility = "hidden";

	forms.forEach(form =>form.style.visibility = "hidden");
	forms[3].style.visibility = "visible";

	btns.forEach(btn => btn.style.visibility = "hidden");
    btns[5].style.visibility = "visible";
    btns[6].style.visibility = "visible";
}
//temp();
