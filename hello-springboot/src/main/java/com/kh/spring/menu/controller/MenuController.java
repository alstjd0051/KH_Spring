package com.kh.spring.menu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.menu.model.service.Menuservice;
import com.kh.spring.menu.model.vo.Menu;

import lombok.extern.slf4j.Slf4j;

/**
 * @RestController = @Controller + @ResponseBody 모든 handler는 @ResponseBody로
 *                 처리된다.
 *
 */
@RestController
@Slf4j
public class MenuController {

	@Autowired
	private Menuservice menuService;

	@GetMapping("/menus")
	public List<Menu> menus() {
		try {
			// 1. 업무로직
			List<Menu> list = menuService.selectMenuList();
			log.debug("list = {}", list);
			// 2. 리턴하면 @ResponseBody에 의해서 json변환후, 응답출력 처리
			return list;
		} catch (Exception e) {
			log.error("/menus 오류!", e);
			throw e;
		}
	}

	/**
	 * @PathVariable 경로변수
	 * 
	 * @return
	 */
	@GetMapping("/menus/{type}/{taste}")
	public List<Menu> menuByType(@PathVariable String type, @PathVariable String taste) {
		try {
			log.debug("type = {}, taste ={}", type, taste);
			Map<String, Object> param = new HashMap<>();
			param.put("type", type);
			param.put("taste", taste);

			List<Menu> list = menuService.selectMenuListByTypeAndTaste(param);
			log.debug("list = {}", list);
			return list;
		} catch (Exception e) {
			log.error("/menus/type 오류!", e);
			throw e;
		}
	}
	
	/**
	 * @RequestBody 요청메시지 body에ㅔ 작성된 json 문자열을 java객체로 변환
	 * 
	 * @param menu
	 * @return
	 */
	
	@PostMapping("/menu")
	public Map<String, Object> insertMenu(@RequestBody Menu menu) {
		try {
			log.debug("menu =", menu);
			int result = menuService.insertMenu(menu);
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "메튜등록 성공!");
			return map;
		} catch (Exception e) {
			log.error("메뉴 등록 실패!", e);
			throw e;
		}
	}
}
