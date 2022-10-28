package com.joongbu.fakerly.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import com.joongbu.fakerly.dto.QnaDto;
import com.joongbu.fakerly.dto.QnaImgDto;
import com.joongbu.fakerly.dto.QnaRolesDto;
import com.joongbu.fakerly.mapper.QnaImgMapper;
import com.joongbu.fakerly.mapper.QnaMapper;
import com.joongbu.fakerly.service.QnaService;
import com.joongbu.fakerly.dto.UserDto;

@Controller
@RequestMapping("/qnaboard")
public class QnaController {
	@Autowired
	QnaMapper qnaMapper;
	@Autowired
	QnaService QnaService;
	@Value("${spring.servlet.multipart.location}")
	String imgSavePath;
	@GetMapping("/qalist.do")
	public String qalist(
		Model model)
	{
//		final int ROWS=5;
		List<QnaDto> qaList=null;
		
		try {
			//list 쿼리를 실행하기 전에 PageHelper.startpage만 호출하면 자동으로 Paging과 count쿼리를 실행 
//			PageHelper.startPage(page,Rows,"board_no DESC");
			
			qaList=qnaMapper.qalist();
			System.out.println(qaList);
			System.out.println("QALIST실행완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("qaList",qaList);
		return "/qnaboard/qalist";
	}

	@GetMapping("/qadetail.do")
	public String qadetail(
		@RequestParam(required=true)int qaNo,
		Model model
			) {
		QnaDto qadetail=null;
		try {
			qadetail=qnaMapper.qadetail(qaNo);
			System.out.println(qadetail);
			System.out.println("QADETAIL실행완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("qnaboard",qadetail);
		return "/qnaboard/qadetail";
	}
	
	@GetMapping("/qaupdate.do")
	public String qaupdate(
		@RequestParam(required=true)int qaNo,
		Model model,
		@SessionAttribute(required = false) UserDto loginUser,
		HttpSession session
		)	{
		
		QnaDto qadetail=null;
		String msg="";
		try {
			if(loginUser!=null) {
				qadetail=qnaMapper.qadetail(qaNo);
				System.out.println(qadetail);
				System.out.println("QAUPDATE실행완료");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//qadetail, loginUser가 null아니고, qadetail에서 가져온 user_no와 loginUser의 user_no가 같으면 실행
		if(qadetail!=null&& loginUser!=null && qadetail.getUser().getUser_no()==loginUser.getUser_no() ) {
			model.addAttribute("qnaboard",qadetail);
			System.out.println("ㅇㅇㅇㅇㅇㅇ");
			return "/qnaboard/qaupdate";
		}else {
			System.out.println("bbbb");
			msg=(loginUser==null)?"로그인하셔야 이용하실 수 있습니다":"글쓴이만 수정 가능합니다";
			session.setAttribute("msg", msg);
			return "redirect:/qnaboard/qadetail.do?qaNo="+qaNo;
		}
	}
		
	
	@PostMapping("/qaupdate.do")
	public String qaupdate(
			QnaDto qna) {
		int qaupdate=0;
		try {
			System.out.println("QAUPDATE POST");
			qaupdate=qnaMapper.qaupdate(qna);
			System.out.println(qaupdate);
		} catch (Exception e) {
			e.printStackTrace();
		}if(qaupdate>0) {
			return "redirect:/qnaboard/qadetail.do?qaNo="+qna.getQaNo();
		}else {
			return "redirect:/qnaboard/qaupdate.do?qaNo="+qna.getQaNo();
		}
	}
	

	@GetMapping("/qadelete.do")
	public String qadelete(
			@RequestParam(required = true) int qaNo
			) {
		int qadelete=0;
		try {
			System.out.println("QADELETE POST");
//			qaupdate=qnaMapper.qaupdate(qna);
			qadelete=qnaMapper.qadelete(qaNo);
			System.out.println(qadelete);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(qadelete>0) {
			return "redirect:/qnaboard/qalist.do?";
		}else {
			return "redirect:/qnaboard/qaupdate.do?qaNo="+qaNo;
		}
		
	}
	
	@GetMapping("/qainsert.do")
	public String qainsert(

		@SessionAttribute(required = false) UserDto loginUser,
		HttpSession session
			)	{
		String msg="";
	if(loginUser!=null) {
		return "/qnaboard/qainsert";
	}else {
		msg="로그인하셔야 게시글을 작성할 수 있습니다.";
		session.setAttribute("msg", msg);
		return "redirect:/user/login.do";
	}
	}
	
	@PostMapping("/qainsert.do")
	public String qainsert(
			QnaDto qna,
			@SessionAttribute(required = false) UserDto loginUser,
			HttpSession session,
			@RequestParam(name="img") MultipartFile [] imgs
			) {
		int qainsert=0;
//		int qaimginsert=0;
		String msg="";
		ArrayList<String>qaimgPaths = new ArrayList<String>();
		try {
			for(MultipartFile img: imgs) {
				if(!img.isEmpty()) {
					String[] contentTypes=img.getContentType().split("/");
					if(contentTypes[0].equals("image")) {
						String fileName="qnaboard_"+System.currentTimeMillis()+"."+contentTypes[1];

						Path path=Paths.get(imgSavePath+"/"+fileName);
						img.transferTo(path);
						System.out.println("파일이미지 저장됏나요?");
						qaimgPaths.add(fileName);
						System.out.println(qaimgPaths);
					}
				}
			}
			
			
			if(loginUser!=null) {
				qna.setUser(loginUser);
//				qainsert=qnaMapper.qainsert(qna);
				qainsert=QnaService.registQnaAndQnaImgs(qna, qaimgPaths);
				System.out.println("일단 로그인성ㄱㅇ하고 ");
				System.out.println(qainsert);
		} 
		}catch (Exception e) {
		}
		if(qainsert>0) {
			msg="게시글 작성 성공";
			session.setAttribute("msg", msg);
			return "redirect:/qnaboard/qadetail.do?qaNo="+qna.getQaNo();			
		}else {
			System.out.println("일단여기가문젠듯?");
			System.out.println(qainsert);
			System.out.println(loginUser);
			msg="게시글 작성 실패(db오류)";
		}
		session.setAttribute("msg", msg);
		return "redirect:/qnaboard/qainsert.do";
}
	
}
