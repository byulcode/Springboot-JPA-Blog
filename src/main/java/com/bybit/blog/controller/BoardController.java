package com.bybit.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bybit.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	// @AuthenticationPrincipal PrincipalDetail principal
	@GetMapping({"","/"})
	public String index() {	// 컨트롤러에서 세션에 접근하는 방법
		return "index";
	}
	
	//USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
