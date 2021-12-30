package com.bybit.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bybit.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	@GetMapping({"","/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal) {	// 컨트롤러에서 세션에 접근하는 방법
		System.out.println("로그인 사용자 아이디 : " + principal.getUsername());
		return "index";
	}
}
