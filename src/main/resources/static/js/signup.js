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