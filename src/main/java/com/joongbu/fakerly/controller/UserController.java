package com.joongbu.fakerly.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joongbu.fakerly.dto.UserDto;
import com.joongbu.fakerly.mapper.UserMapper;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	UserMapper userMapper;

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String toEmail, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("tiger981105@gmail.com");
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);

		mailSender.send(message);

		System.out.println("Mail Sent successfully...");
	}

	@Autowired
	private EmailSenderService senderService;

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
			@RequestParam(required = true) String pw
			) {
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

				// DB에서 유저 찾기
				if (loginUser != null) {
					// 비밀번호 비교
					// Boolean bool = BCrypt.checkpw(pw, loginUser.getPw());
					if (loginUser.getPw().charAt(0) == '$') {
						// Bcrypt 암호화된 패스워드
						if (!BCrypt.checkpw(pw, loginUser.getPw())) {
							msg = "비밀번호가 일치하지 않습니다.";
							session.setAttribute("msg", msg);
							return "redirect:/user/login.do";
						} else {
							session.setAttribute("loginUser", loginUser);
							System.out.println(session.getAttribute("loginUser"));
							msg = "로그인 성공!";
							session.setAttribute("msg", msg);
							return "redirect:/mainboard/main"; // 메인 페이지로 이동
						}
					} else {
						// 임시 패스워드
						if (!pw.equals(loginUser.getPw())) {
							msg = "비밀번호가 일치하지 않습니다.";
							session.setAttribute("msg", msg);
							return "redirect:/user/login.do";
						} else {
							session.setAttribute("loginUser", loginUser);
							System.out.println(session.getAttribute("loginUser"));
							msg = "로그인 성공!";
							session.setAttribute("msg", msg);
							return "redirect:/mainboard/main"; // 메인 페이지로 이동
						}
					}
				} else {
					msg = "입력하신 이메일로 가입한 회원이 없습니다.";
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
			RedirectAttributes redirAtt,
			Model model
			) throws MessagingException, IOException {
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
							// redirAtt.addFlashAttribute("pw", findUserPassword.getPw().substring(0, 3) +
							// "****");
							// redirAtt.addFlashAttribute("pw", uuid);

							String userEmail = findUserPassword.getEmail();

							String uuid = UUID.randomUUID().toString().replaceAll("-", "");
							uuid = uuid.substring(0, 13);

							// String emailSubject = "[Fakerly] 임시 비밀번호가 발급되었습니다.";
							// String emailbody = uuid + "\n" + "로그인하러 가기" + "\n" +
							// "http://localhost:8081/user/login.do";
							// emailbody += "\n<a href='http://localhost:8081/user/login.do'>Fakerly 로그인하러
							// 가기</a>";
							// senderService.sendEmail(userEmail, emailSubject, emailbody);

							HashMap<String, String> emailValues = new HashMap<String, String>();
							System.out.println(findUserPassword.getUser_name());
							emailValues.put("userName", findUserPassword.getUser_name());
							emailValues.put("tempPassword", uuid);

							String emailTitle = "[Fakerly] 임시 비밀번호가 발급되었습니다.";
							senderService.sendEmail(emailTitle, findUserPassword.getEmail(), "tempPassword",
									emailValues);
							System.out.println("이메일 전송 완료");

							// DB에 임시 비밀번호 저장
							userMapper.giveTempPassword(userEmail, uuid);
							
							String[] emailArr = userEmail.split("@|\\.");
							System.out.println(Arrays.toString(emailArr));
							System.out.println(emailArr[1]);
							switch (emailArr[1]) {
							case "gmail":
								model.addAttribute("domain", "gmail");
								break;
							case "naver":
								model.addAttribute("domain", "naver");
								break;
							}

							// return redirPasswordSuccess;
							return "/user/findPasswordSuccess";
						}
					} else {
						return redirFail;
					}
				}
			}

	// 이메일 찾기 성공
	@GetMapping("/findEmailSuccess.do")
	public void findEmailSuccess(HttpServletRequest req) {
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
	public void findPasswordSuccess(HttpServletRequest req, HttpSession session) {
		System.out.println("\n" + req.getMethod() + "\t" + req.getRequestURI());
		Enumeration params = req.getParameterNames();
		System.out.print("Parameter> ");
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			System.out.print(name + " : " + req.getParameter(name) + "\t");
		}
		System.out.println();
	}

	// 이메일/비밀번호 찾기 실패
	@GetMapping("/findEmailPasswordFail.do")
	public void findEmailPasswordFail(HttpServletRequest req) {
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
	public String logout(HttpServletRequest req, HttpSession session) {
		String msg = "";
		System.out.println("\n" + req.getMethod() + "\t" + req.getRequestURI());
		Enumeration params = req.getParameterNames();
		System.out.print("Parameter> ");
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			System.out.print(name + " : " + req.getParameter(name) + "\t");
		}
		System.out.println();

		session.removeAttribute("loginUser");
		msg = "로그아웃 되었습니다.";
		session.setAttribute("msg", msg);
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
	public void signup() {
	}

	@PostMapping("/insert.do")
	public String insert(UserDto user) {

		int insert = 0;
		int update1 = 0;
		int update2 = 0;
		int update3 = 0;
		System.out.println(user);

		// calc age
		Date birth = user.getBirth();
		Date now = new Date(System.currentTimeMillis());
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		user.setAge(Integer.parseInt(yearFormat.format(now)) - Integer.parseInt(yearFormat.format(birth)));

		// pw 암호화
		user.setPw(BCrypt.hashpw(user.getPw(), BCrypt.gensalt()));

		try {
			insert = userMapper.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			update1 = userMapper.updateUserKeyword();
			update2 = userMapper.updateUserSkill();
			update3 = userMapper.updateUserLicense();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (insert > 0) {
			return "/user/login";
		} else {
			return "redirect:/user/signup.do";
		}
	}

	@GetMapping("/checkNickname.do")
	public @ResponseBody Boolean checkNickname(@RequestParam(required = true) String nickname) {

		Boolean exist = false;
		UserDto user = null;

		try {
			user = userMapper.checkNickname(nickname);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (user != null)
			exist = true;
		else
			exist = false;

		return exist;
	}
}
