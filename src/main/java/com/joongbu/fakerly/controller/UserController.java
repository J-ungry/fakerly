package com.joongbu.fakerly.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.bind.annotation.RequestMapping;


import com.joongbu.fakerly.dto.UserDto;
import com.joongbu.fakerly.mapper.UserMapper;

import lombok.Getter;
import lombok.Setter;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	UserMapper userMapper;


	// 로그인 페이지
	@GetMapping("/login.do")
	public void login(HttpServletRequest req) {
		System.out.println("\n" + req.getMethod() + "\t" + req.getRequestURI());
		Enumeration params = req.getParameterNames();
		System.out.print("Parameter> ");
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			System.out.print(name + " : " + req.getParameter(name) + "\t");
		}
		System.out.println();
	}

	// 로그인 시도
	@PostMapping("/login.do")
	public String login(
			HttpServletRequest req, 
			HttpSession session, 
			@RequestParam(required = true) String email,
			@RequestParam(required = true) String pw) {
		System.out.println("\n" + req.getMethod() + "\t" + req.getRequestURI());
		Enumeration params = req.getParameterNames();
		System.out.print("Parameter> ");
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			System.out.print(name + " : " + req.getParameter(name) + "\t");
		}
		System.out.println();

		UserDto loginUser = null;
		String msg = "";

		try {
			loginUser = userMapper.login(email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (loginUser != null) {
			// 비밀번호 비교
			if (!pw.equals(loginUser.getPw())) {
				System.out.println("입력한 비밀번호가 틀려요.");
				msg = "비밀번호가 일치하지 않습니다.";
				session.setAttribute("msg", msg);
				return "redirect:/user/login.do";
			} else {
				session.setAttribute("loginUser", loginUser);
				System.out.println("로그인 성공, 세션 생성");
				msg = "로그인 성공!";
				session.setAttribute("msg", msg);
				return "redirect:/"; // 메인 페이지로 이동
			}
		} else {
			System.out.println("입력한 email에 맞는 user가 없어요");
			msg = "입력하신 이메일로 가입한 회원이 없습니다.";
			// 회원가입 하시겠습니까 물어보면서 회원가입 페이지로 넘길까?
			session.setAttribute("msg", msg);
			return "redirect:/user/login.do";
		}

	}

	// 이메일/비밀번호 찾기 페이지
	@GetMapping("/findEmailPassword.do")
	public void findEmailPassword(HttpServletRequest req) {
		System.out.println("\n" + req.getMethod() + "\t" + req.getRequestURI());
		Enumeration params = req.getParameterNames();
		System.out.print("Parameter> ");
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			System.out.print(name + " : " + req.getParameter(name) + "\t");
		}
		System.out.println();
	}

	// 이메일/비밀번호 찾기 시도
	@PostMapping("/findEmailPassword.do")
	public String findEmailPassword(
			HttpServletRequest req, 
			String fEmail, 
			@RequestParam(required = true) String fName,
			@RequestParam(required = true) String fPhone, 
			@RequestParam(required = true) String fBirth,
			HttpSession session,
			RedirectAttributes redirAtt
			) {
				System.out.println("\n" + req.getMethod() + "\t" + req.getRequestURI());
				Enumeration params = req.getParameterNames();
				System.out.print("Parameter> ");
				while (params.hasMoreElements()) {
					String name = (String) params.nextElement();
					System.out.print(name + " : " + req.getParameter(name) + "\t");
				}
				System.out.println();

				UserDto findUserEmail = null;
				UserDto findUserPassword = null;
				Date date = new Date();
				SimpleDateFormat tranSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String redirEmailSuccess = "redirect:/user/findEmailSuccess.do";
				String redirPasswordSuccess = "redirect:/user/findPasswordSuccess.do";
				String redirFail = "redirect:/user/findEmailPasswordFail.do";

				if (fEmail == null) { // 이메일 찾기
					try {
						findUserEmail = userMapper.findUserEmail(fPhone);
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (findUserEmail != null) {
						System.out.println(findUserEmail);
						if (!fName.equals(findUserEmail.getUser_name())) {
							return redirFail;
						} else if (!fBirth.equals(tranSimpleDateFormat.format(findUserEmail.getBirth()))) {
							return redirFail;
						} else {
							redirAtt.addFlashAttribute("email", findUserEmail.getEmail());
//							session.setAttribute("status", 1);
							return redirEmailSuccess;
						}
					} else {
						return redirFail;
					}
				} else { // 비밀번호 찾기
					try {
						findUserPassword = userMapper.findUserPassword(fEmail);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (findUserPassword != null) {
						if (!fName.equals(findUserPassword.getUser_name())) {
							return redirFail;
						} else if (!fPhone.equals(findUserPassword.getPhone())) {
							return redirFail;
						} else if (!fBirth.equals(tranSimpleDateFormat.format(findUserPassword.getBirth()))) {
							return redirFail;
						} else {
							redirAtt.addFlashAttribute("pw",findUserPassword.getPw());
//							session.setAttribute("status", 1);
							return redirPasswordSuccess;
						}
					} else {
						return redirFail;
					}
				}
			}
	
	// 이메일 찾기 성공
	@GetMapping("/findEmailSuccess.do")
	public void findEmailSuccess(
			HttpServletRequest req
			) {
				System.out.println("\n" + req.getMethod() + "\t" + req.getRequestURI());
				Enumeration params = req.getParameterNames();
				System.out.print("Parameter> ");
				while (params.hasMoreElements()) {
					String name = (String) params.nextElement();
					System.out.print(name + " : " + req.getParameter(name) + "\t");
				}
				System.out.println();				
	}
	
	
	// 비밀번호 찾기 성공
	@GetMapping("/findPasswordSuccess.do")
	public void findPasswordSuccess(
			HttpServletRequest req,
			HttpSession session
			) {
				System.out.println("\n" + req.getMethod() + "\t" + req.getRequestURI());
				Enumeration params = req.getParameterNames();
				System.out.print("Parameter> ");
				while (params.hasMoreElements()) {
					String name = (String) params.nextElement();
					System.out.print(name + " : " + req.getParameter(name) + "\t");
				}
				System.out.println();
				System.out.println(session.getAttribute("status"));
	}

	
	// 이메일/비밀번호 찾기 실패
	@GetMapping("/findEmailPasswordFail.do")
	public void findEmailPasswordFail(
			HttpServletRequest req
			) {
				System.out.println("\n" + req.getMethod() + "\t" + req.getRequestURI());
				Enumeration params = req.getParameterNames();
				System.out.print("Parameter> ");
				while (params.hasMoreElements()) {
					String name = (String) params.nextElement();
					System.out.print(name + " : " + req.getParameter(name) + "\t");
				}
				System.out.println();
	}

	// 로그아웃
	@GetMapping("/logout.do")
	public String logout(
			HttpServletRequest req, 
			HttpSession session
			) {
				System.out.println("\n" + req.getMethod() + "\t" + req.getRequestURI());
				Enumeration params = req.getParameterNames();
				System.out.print("Parameter> ");
				while (params.hasMoreElements()) {
					String name = (String) params.nextElement();
					System.out.print(name + " : " + req.getParameter(name) + "\t");
				}
				System.out.println();

				// session.invalidate();
				session.removeAttribute("loginUser");
				return "redirect:/";
			}
	
	// JBcrypt 테스트
	// http://localhost:8888/user/crypt?email=123@gmail.com
	@GetMapping("/crypt")
	public @ResponseBody String crypt(String email) {
		UserDto loginUser = null;
		try {
			loginUser = userMapper.login(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String pw = loginUser.getPw();
		String hashedPw = BCrypt.hashpw(pw, BCrypt.gensalt());
		Boolean bool = BCrypt.checkpw(pw, hashedPw);
		
		return hashedPw + " " + bool;
	}

// @ResponseBody
// RedirectAttributes

	
	@GetMapping("/signup.do")
	public void signup() {}
	
	@PostMapping("/insert.do")
	public String insert(UserDto user) {
		
		int insert = 0;
		System.out.println(user);
		
		//calc age
		Date birth = user.getBirth();
		Date now = new Date(System.currentTimeMillis());
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy"); 
		user.setAge(Integer.parseInt(yearFormat.format(now)) - Integer.parseInt(yearFormat.format(birth)));
		
		//pw 암호화
		user.setPw(BCrypt.hashpw(user.getPw(), BCrypt.gensalt()));
		
		try {
			insert = userMapper.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(insert>0) {
			return "/user/login";
		}else {
			return "redirect:/user/signup.do";
		}
	}
	
	@GetMapping("/checkNickname.do")
	public @ResponseBody Boolean checkNickname(@RequestParam(required = true)String nickname) {
		
		Boolean exist = false;
		UserDto user = null;
		
		try {
			user = userMapper.checkNickname(nickname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(user != null) exist = true;
		else exist = false;
		
		return exist;
	}
}


