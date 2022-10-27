package com.joongbu.fakerly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joongbu.fakerly.dto.QnaDto;
import com.joongbu.fakerly.dto.QnaRolesDto;
import com.joongbu.fakerly.mapper.QnaMapper;
import com.joongbu.fakerly.mapper.QrolesMapper;

@Controller
@RequestMapping("/qnaboard")
public class QnaController {
	@Autowired
	QnaMapper qnaMapper;
	@GetMapping("/qalist.do")
	public String qalist(
		Model model)
	{
		List<QnaDto> qaList=null;
		
		try {
			qaList=qnaMapper.qalist();
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
	@GetMapping("/update.do")
	public String update(
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
}
