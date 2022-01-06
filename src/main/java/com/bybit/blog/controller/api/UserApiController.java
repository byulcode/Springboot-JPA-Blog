package com.bybit.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bybit.blog.config.auth.PrincipalDetail;
import com.bybit.blog.dto.ResponseDto;
import com.bybit.blog.model.RoleType;
import com.bybit.blog.model.User;
import com.bybit.blog.service.UserService;

@RestController
public class UserApiController { 

	@Autowired
	private UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	//회원가입
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {//username, password, email
		System.out.println("UserApiController : save 호출됨");
		//실제로 DB에 insert를 하고 아래에서 return이 되면 성공!
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);	//자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
	}

	//회원정보 수정
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){// key=value, x-www-form-urlencoded
		userService.회원수정(user);
		// 여기서는 트랜잭션이 종료되기 때문에 DB의 값은 변경이 됐음
		// But 세션 값이 변경이 되지 않은 상태이기 때문에 직접 세션값을 변경해줄 것

		//세션 등록
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}



//기본 로그인 방식
//@PostMapping("/api/user/login")
//public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
//	System.out.println("UserApiController : login호출됨");
//	User principal = userService.로그인(user); // principal : 접근주체
//	
//	if(principal != null) {
//		session.setAttribute("principal", principal);
//	}
//	
//	return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//}