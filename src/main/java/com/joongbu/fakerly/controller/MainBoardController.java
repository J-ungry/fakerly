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

	
//	@GetMapping("/main")	// /mainboard/main GET 요청 처리
//	public String main() {
//		return "/mainboard/main";
//	}
	
	@GetMapping("/main")	// /mainboard/main GET 요청 처리
	public String main(Model model) {
		List<MainBoardDto> mainList=null;
		try {
			mainList=boardMapper.list();
			System.out.println(mainList.get(0).getPrefer());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("mainList", mainList);
		return "/mainboard/main";
	}
	
	
	@GetMapping("/insert")
	public void insert() {}
	@PostMapping("/insert")
	public String insert(
			MainBoardDto mainboard,
			HttpSession session,
			@SessionAttribute(required=true) UserDto loginUser
			) {
		System.out.println(mainboard);
		String msg="";
		int insert=0;
		try {
			if(loginUser!=null) {
				insert=boardMapper.insert(mainboard);
				System.out.println(mainboard);
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
			@RequestParam(required=true)int mainboardNo,
			Model model
			) {
		MainBoardDto mainboard=null;
		
		try {
			mainboard=boardMapper.detailReply(mainboardNo);
			System.out.println(mainboard);
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
	
	

	
	
}







