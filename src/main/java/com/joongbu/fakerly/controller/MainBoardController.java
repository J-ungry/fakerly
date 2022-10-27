package com.joongbu.fakerly.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.joongbu.fakerly.dto.MainBoardDto;
import com.joongbu.fakerly.dto.UserDto;
import com.joongbu.fakerly.mapper.MainBoardMapper;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@RequestMapping("/mainboard")	// /mainboard 요청 매핑
@Controller
public class MainBoardController {
	@Autowired
	DataSource dataSource;
	@Autowired
	MainBoardMapper boardMapper;
	
	@GetMapping("/main")	// /mainboard/main GET 요청 처리
	public String main(Model model) {
		List<MainBoardDto> mainList=null;
		try {
			mainList=boardMapper.list();
			System.out.println(mainList.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("mainList", mainList);
		return "/mainboard/main";
	}
	
	//게시글 등록 get
	@GetMapping("/insert")
	public String insert(	
			@SessionAttribute(required=false) UserDto loginUser,
			HttpSession session
			) {
		String msg="";
		if(loginUser!=null) {
			return "/mainboard/insert";
		} else {
			msg="로그인하셔야 게시글을 작성할 수 있습니다.";
			session.setAttribute("msg", msg);
			return "redirect:/user/login.do";
		}
	}
	
	//게시글 등록 post
	@PostMapping("/insert")
	public String insert(
			MainBoardDto mainboard,
			HttpSession session,
			@SessionAttribute(required=true) UserDto loginUser
			) {
		String msg="";
		int insert=0;
		try {
			if(loginUser!=null) {
				if(mainboard.getMainboardTitle().isEmpty()) {
					msg="게시글의 제목을 입력하세요.";
					session.setAttribute("msg", msg);
					return "redirect:/mainboard/insert?userNo"+loginUser.getUser_no();
				} else if(mainboard.getMainboardContents().isEmpty()) {
					msg="게시글의 내용을 입력하세요.";
					return "redirect:/mainboard/insert?userNo"+loginUser.getUser_no();
				} else {
					insert=boardMapper.insert(mainboard);
				}
			} else {
				msg="로그인 한 사용자만 글을 등록할 수 있습니다.";
				session.setAttribute("msg", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(insert>0) {
			msg="게시글 등록 성공";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/main";
		} else {
			msg="게시글 등록 실패";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/insert";
		}
	}
	
	@GetMapping("/detail")
	public String detail(
			@RequestParam(required=true) int mainboardNo,
			Model model
			) {
		MainBoardDto mainboard=null;
		try {
			mainboard=boardMapper.detailReply(mainboardNo);
			System.out.println("나는야 퉁퉁이"+mainboard);
			//boardMapper.viewUpdate(mainboardNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(mainboard!=null) {
			model.addAttribute("mainboard",mainboard);
			return "/mainboard/detail";
		}else {
			return "redirect:/mainboard/main";
		}
	}
	
	//mainboard 게시글 삭제
	@GetMapping("/delete")
	public String delete(
			@RequestParam(required=true) int mainboardNo,
			@SessionAttribute(required=false) UserDto loginUser,
			HttpSession session
			) {
		int delete=0;
		String msg="";
		try {
			if(loginUser!=null) {
				MainBoardDto mainboard=boardMapper.detail(mainboardNo);
				if(mainboard.getUserNo()==loginUser.getUser_no()) {
					delete=boardMapper.delete(mainboardNo);
				} else {
					msg="글쓴이만 삭제 할 수 있습니다.";
				}
			} else {
				msg="로그인해야 게시글 삭제가 가능합니다.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(delete>0) {
			msg="삭제 성공";
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/main";
		} else {
			session.setAttribute("msg", msg);
			return "redirect:/mainboard/main";
		}
	}

	
}







