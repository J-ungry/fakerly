package com.joongbu.fakerly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.joongbu.fakerly.interceptor.MainBoardCheckInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Autowired
	MainBoardCheckInterceptor mainBoardCheckInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(mainBoardCheckInterceptor)
			.addPathPatterns("/mainboard/insert")
			.addPathPatterns("/mainboard/delete")
			.addPathPatterns("/mainboard/update");
	}
}
