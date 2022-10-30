package com.joongbu.fakerly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joongbu.fakerly.dto.QnaDto;
import com.joongbu.fakerly.dto.QnaImgDto;
import com.joongbu.fakerly.mapper.QnaImgMapper;
import com.joongbu.fakerly.mapper.QnaMapper;

@Service  //@AutoWired로 객체 주입 가능
public class QnaService {
	@Autowired
	QnaMapper QnaMapper;
	@Autowired
	QnaImgMapper QnaImgMapper;
	
	public int registQnaAndQnaImgs(QnaDto qna, List<String>qaimgPaths) {
		int qainsert=0;
		qainsert=QnaMapper.qainsert(qna);
		
		int qaimginsert=0;
		for(String img_path:qaimgPaths) {
			QnaImgDto qna_img=new QnaImgDto();
			qna_img.setQaNo(qna.getQaNo()); //qna 게시글 번호 넣어줘야댐
//			qna_img.setQaImgNo(qaimginsert);
			qna_img.setImg_path(img_path);
			qaimginsert+=QnaImgMapper.qainsert(qna_img);
		}
		System.out.println("저장된 이미지 db수 :"+qaimginsert);
		return qainsert;
		
	
	
	}
}

