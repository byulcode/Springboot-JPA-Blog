package com.bybit.blog.test;
//Builder 장점 : 객체에 값을 넣을 때 순서를 지키지 않아도 된다. / 객체 값 순서를 헷갈려서 객체의 값을 잘못 넣는 실수를 방지한다

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.experimental.PackagePrivate;
//사용자가 요청 -> 응답(HTML 파일)
//@Controller

//사용자가 요청 -> 응답(Data)
@RestController

public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("byul").password("1234").email("byul@naver.com").build();
		System.out.println(TAG+"getter : "+m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"setter : "+m.getUsername());
		return "lombok test 완료";
	}
	
	//인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다!!!!
	// http://localhost:8080/http/get		(select)
	@GetMapping("/http/get")
	public String getTest(Member m) {// MessageConverter(스프링부트)
		// id=1&username=ssar&password=1234&email=ssar@naver.com
		return "request get : " + m.getId()+", "+m.getUsername()+","+m.getPassword()+", "+m.getEmail();	
	}
	
	// http://localhost:8080/http/post 		(insert)b
	@PostMapping("/http/post") // text/plain, application/json
	public String postTest(@RequestBody Member m) {// MessageConverter(스프링부트)
		return "request post : "  + m.getId()+", "+m.getUsername()+","+m.getPassword()+", "+m.getEmail();	
	}
	
	// http://localhost:8080/http/put		(update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "request put"+ m.getId()+", "+m.getUsername()+","+m.getPassword()+", "+m.getEmail();	
	}
	
	// http://localhost:8080/http/delete 	(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "request delete";
	}
	
}
