package com.joongbu.fakerly.controller;

import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joongbu.fakerly.dto.UserDto;
import com.joongbu.fakerly.mapper.UserMapper;

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
	public @ResponseBody String login(HttpServletRequest req, HttpSession session,
			@RequestParam(required = true) String email, @RequestParam(required = true) String pw) {
		System.out.println("\n" + req.getMethod() + "\t" + req.getRequestURI());
		Enumeration params = req.getParameterNames();
		System.out.print("Parameter> ");
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			System.out.print(name + " : " + req.getParameter(name) + "\t");
		}
		System.out.println();

		UserDto loginUser = null;

		try {
			loginUser = userMapper.login(email, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (loginUser != null) {
			session.setAttribute("loginUser", loginUser);
			System.out.println(loginUser.getBirth());
			System.out.println(loginUser.getSignup());
			System.out.println("로그인 성공, 세션 생성");
			return "성공";
		} else {
			System.out.println("로그인 실패");
			return "실패";
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
	/*@PostMapping("/findEmailPassword.do")
	public String findEmailPassword(HttpServletRequest req, 
			String fEmail, 
			@RequestParam(required = true) String fName,
			@RequestParam(required = true) String fPhone, 
			@RequestParam(required = true) Date fBirth) {
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
		

		if (fEmail == null) {
			System.out.println("이메일 찾기");
			try {
				findUserEmail = userMapper.findUserEmail(fName, fPhone, fBirth);
				System.out.println("이메일 찾기2");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(findUserEmail != null) {
				System.out.println(findUserEmail);
				return "이메일 찾기 성공";
			} else {				
				return "이메일 찾기 실패";
			}
		} else {
			System.out.println("비밀번호 찾기");
			try {
				findUserPassword = userMapper.findUserPassword(fEmail, fName, fPhone, fBirth);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (findUserPassword != null) {
				System.out.println(findUserPassword.getPw());
				return "비밀번호 찾기 성공";
			} else {
				return "비밀번호 찾기 실패";
			}
		}
	}*/
	
	// 로그아웃
	@GetMapping("/logout.do")
	public String logout(HttpServletRequest req, HttpSession session) {
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
}
