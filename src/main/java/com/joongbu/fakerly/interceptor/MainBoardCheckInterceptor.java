package com.joongbu.fakerly.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MainBoardCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//mainboard 컨트롤러 요청하기 전 로그인 예외처리
		HttpSession session=request.getSession();
		Object loginUser_obj=session.getAttribute("loginUser");
		if(loginUser_obj!=null) {
			return true;
		} else {
			session.setAttribute("msg", "로그인 후 이용 가능합니다.");
			response.sendRedirect("/user/login.do");
			return false;
		}
	}
}
