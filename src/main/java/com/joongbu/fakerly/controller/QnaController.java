package com.joongbu.fakerly.controller;

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

import com.joongbu.fakerly.dto.QnaDto;
import com.joongbu.fakerly.dto.QnaRolesDto;
import com.joongbu.fakerly.mapper.QnaMapper;

@Controller
@RequestMapping("/qnaboard")
public class QnaController {
	@Autowired
	QnaMapper qnaMapper;
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
//		List<QnaRolesDto> qnaroles=null;
		try {
			qadetail=qnaMapper.qadetail(qaNo);
//			qnaroles=QrolesMapper.list(qaNo);
			System.out.println(qadetail);
//			qnaMapper.viewUpdate(qnaboard);
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
		Model model
		)	{

		QnaDto qadetail=null;
		
		try {
			qadetail=qnaMapper.qadetail(qaNo);
			System.out.println(qadetail);
			System.out.println("QAUPDATE실행완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("qnaboard",qadetail);
		return "/qnaboard/qaupdate";
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
	
//	@GetMapping("/qainsert.do")
//	public String qainsert(
//			QnaDto qna,
//			Model model
//			) {
//		List<QnaDto> qainsertList=null;
//		try {
//			qainsertList=qnaMapper.qainsert(qna);
//			System.out.println("QADINSERT GET");
//			System.out.println(qainsertList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return "redirect:/qnaboard/qainsert.do";
//		
//	}
//	
//	@PostMapping("/qainsert.do")
//	public String qainsert(
//			QnaDto qna)
//
//	{
//		int qainsert =0;
//		try {
//			System.out.println("QAINSERT POST");
////			qainsert=qnaMapper.qainsert(qna);
//			System.out.println(qainsert);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if(qainsert>0) {
//			return "redirect:/qnaboard/detail.do?qaNo="+qna.getQaNo();
//		}else {
//			System.out.println("게시글 작성 실패");
//		}
//		return "redirect:/qnaboard/qainsert.do";
//	}
	
}
