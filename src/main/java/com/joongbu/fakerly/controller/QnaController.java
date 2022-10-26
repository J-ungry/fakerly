//package com.joongbu.fakerly.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.joongbu.fakerly.dto.QnaDto;
//import com.joongbu.fakerly.mapper.QnaMapper;
//
//@Controller
//@RequestMapping("/qnaboard")
//public class QnaController {
//	@Autowired
//	QnaMapper qnaMapper;
//	@GetMapping("/qalist.do")
//	public String qalist(
//		Model model)
////		,@RequestParam(defaultValue = "1")int page) 
//	{
//		List<QnaDto> qaList=null;
////		final int ROWS=10;
////		int startRow=(page-1)*ROWS;
//		
//		try {
////			qnaList=qnaMapper.qalist(startRow,ROWS);
//			qaList=qnaMapper.qalist();
////			System.out.println(qaList);
//			System.out.println("tlqkf");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		model.addAttribute("qaList",qaList);
//		return "/qnaboard/qalist";
//	}
//
//	@GetMapping("/qadetail.do")
//	public String qadetail(
//		@RequestParam(required=true)int qaNo,
//		Model model,
//		@RequestParam(defaultValue="1") int page
//			) {
////		System.out.println(qnaboard);
//		QnaDto qnaboard=null;
////		System.out.println(qnaboard);
//		try {
//			qnaboard=qnaMapper.detailReplyPaging();
//			System.out.println(qnaboard);
//			qnaMapper.viewUpdate(qnaboard);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if(qnaboard!=null) {
//			model.addAttribute("qnaboard",qnaboard);
//			return "/qnaboard/qadetail";
//		}else {
//			return "redirect:/qnaboard/qalist.do";
//		}
//	}
//}
