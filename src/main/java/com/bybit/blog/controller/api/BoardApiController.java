package com.bybit.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bybit.blog.config.auth.PrincipalDetail;
import com.bybit.blog.dto.ResponseDto;
import com.bybit.blog.model.Board;
import com.bybit.blog.model.RoleType;
import com.bybit.blog.model.User;
import com.bybit.blog.service.BoardService;
import com.bybit.blog.service.UserService;

@RestController
public class BoardApiController { 
	
	@Autowired
	private BoardService boardService;

	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.글쓰기(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);	
	}

	
}
