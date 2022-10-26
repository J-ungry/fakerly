package com.joongbu.fakerly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joongbu.fakerly.mapper.MainReplyMapper;


@RequestMapping("/reply")
@Controller
public class MainReplyController {
	MainReplyMapper replyMapper;
}
