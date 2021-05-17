package com.kh.spring.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.demo.model.service.DemoService;

/**
 * 사용자 요청 하나당 이를 처리하는 Controller 메소드(Handler)가 하나씩 존재한다.
 * 
 */
@Controller
public class DemoController {
	
	/**
	 * spring용 logging클래스
	 */
	private static final Logger log = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	private DemoService demoService;
	
	/**
	 * 사용자 요청을 처리하는 handler
	 * @return
	 */
	@RequestMapping("/demo/devForm.do")
	public String devForm() {
		log.info("/demo/devForm.do 요청!");
		//viewResolver빈에 의해서 /WEB-INF/views + demo/devForm + .jsp jsp파일로 위임.
		return "demo/devForm";
	}
	
}




