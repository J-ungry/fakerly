package com.joongbu.fakerly.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.joongbu.fakerly.dto.MainBoardDto;
import com.joongbu.fakerly.dto.TempBoardDto;
import com.joongbu.fakerly.dto.UserDto;
import com.joongbu.fakerly.mapper.TempBoardMapper;
		
@RequestMapping("/tempboard")
@Controller
public class TempController {
	@Autowired
	TempBoardMapper tempMapper;
	@GetMapping("/templist.do")
	public String templist(Model model){
		System.out.println("work!");
		List<TempBoardDto> tempList=null;
		try {
			tempList=tempMapper.templist();
			System.out.println(tempList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(tempList==null) {
			return "/mainboard/main";
		}else {
			model.addAttribute("tempList", tempList);
			return "/tempboard/templist";
		}
	}

	@GetMapping("/detail.do")
	public String detail(
			@RequestParam (required = true) int tempNo,
			Model model
			) {
		TempBoardDto tempboard =null;
		try {
			tempboard=tempMapper.detail(tempNo);
			System.out.println("detail!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(tempboard!=null) {
			System.out.println("detail!!!!");
			model.addAttribute("tempboard" ,tempboard);
			return "/tempboard/detail";
		}else {
			System.out.println("ERROR");
			return "redirect:/tempboard/templist.do";
		}
	}
	@GetMapping("/insert.do")
	public String insert(
		TempBoardDto tempboard,
		@SessionAttribute(required = false) UserDto loginUser,
		HttpSession session,
		Model model
		) {
	String msg="";
	if(loginUser!=null) {
		model.addAttribute("tempboard", tempboard);
		return "/tempboard/insert";
	}else {
		msg="로그인 하셔야 게시글 작성이 가능합니다";
		session.setAttribute("msg", msg);
		return "/user/login.do";
	}	
}
	@PostMapping("/insert.do")
	public String insert(
			TempBoardDto tempboard,
			MainBoardDto mainboard,
			@SessionAttribute(required=true) UserDto loginUser,
			HttpSession session
			) {
		String msg="";
		int insert=0;
		try {
			if(mainboard.getMainboardTitle().isEmpty()) {
				msg="제목을 입력해주세요.";
				session.setAttribute("msg", msg);
				return "redirect:/mainboard/insert?userNo"+loginUser.getUser_no();
			}else if(mainboard.getMainboardContents().isEmpty()) {
				msg="게시글을 입력해주세요";
				return "redirect:/tempboard/insert?userNo"+loginUser.getUser_no();
			}else {
				insert=tempMapper.insert(tempboard);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(insert>0) {
			msg="임시보관성공";
			session.setAttribute("msg", msg);
			System.out.println("임시저장성공");
			return "redirect:/mainboard/main";
		}else {
			msg="임시보관실패";
			session.setAttribute("msg", msg);
			return "/mainboard/main";
		}
	}
	@GetMapping("/update.do")
		public String update(
				@RequestParam(required = true) int tempNo,
				Model model,
				@SessionAttribute(required = true)UserDto loginUser,
				HttpSession session
				) {
		TempBoardDto tempboard=null;
		String msg="";
		try {
			tempboard=tempMapper.detail(tempNo);
			System.out.println(tempboard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(tempboard!=null && tempboard.getUserNo()==loginUser.getUser_no()) {
			model.addAttribute("tempboard", tempboard);
			return "/tempboard/update";
		}else if(tempboard.getUserNo()!=loginUser.getUser_no()) {
			msg="글쓴이만 수정 가능";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/main";
		}else {
			System.out.println("ERROR");
			return "mainboard/main";
		}
	}
	@PostMapping("/update.do")
	public String update(
			TempBoardDto tempboard,
			@SessionAttribute(required=false) UserDto loginUser,
			HttpSession session
			) {
		int update=0;
		String msg="";
		try {
			update=tempMapper.update(tempboard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(update>0) {
			msg="업로드 성공";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/main";
		} else {
			msg="업로드 실패";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/update?mainboardNo="+tempboard.getTempNo();
		}
	}
}

/*public String insert(
@SessionAttribute(required = false)UserDto loginUser,
HttpSession session
) {
String msg="";
if(loginUser!=null) {
return"tempboard/insert";
}else {
msg="ERROR";
System.out.println("HEllo");
session.setAttribute("msg", msg);
return "redirect:/user/login.do";
}
}
@PostMapping("/insert.do")
public String insert(
TempBoardDto tempboard,
@SessionAttribute(required = false) UserDto user,
HttpSession session
) {
int insert=0;
String msg="";
try {
if(tempboard!=null) {
	insert=tempboardMapper.insert(tempboard);
}
} catch (Exception e) {
e.printStackTrace();
}
if(insert>0) {
msg="임시 저장 성공";
session.setAttribute("msg", msg);
return "redirect:/tempboard/detail.do?tempNo="+tempboard.getTempNo();
}else {
	msg="게시글 작성 실패(db 오류)";
session.setAttribute("msg", msg);
return "redirect:/mainboard/main.do";
}
}*/

