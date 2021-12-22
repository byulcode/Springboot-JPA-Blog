package com.bybit.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bybit.blog.model.RoleType;
import com.bybit.blog.model.User;
import com.bybit.blog.repository.UserRepository;

//html파일이 아니라 data를 리턴해주는 controller = RestController
@RestController	//응답만
public class DummyControllerTest {
	
	@Autowired	//의존성 주입(DI)
	private UserRepository userRepository;
	
	//http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/user")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한 페이지당 2건의 데이터를 리턴받아볼 예정
	@GetMapping("/dummy/user/page")
	public Page<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> users = userRepository.findAll(pageable);
		return users;
	}
	
	
	//{id} 주소로 파라미터 전달 받을 수 있음
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//만약 user가 null이되면 문제가 생기니 빈객체를 user에 넣도록 하는 것
		User user = userRepository.findById(id).orElseThrow();
		
		//요청 : 웹브라우저
		//user 객체 = 자바 오브젝트
		//변환 ( 웹브라우저가 이해할 수 있는 데이터) -> json 
		// 스프링부트 = MessageConverter라는 애가 응답시 자동 작동
		//만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson라이브러리를 호출해서
		// user오브젝트를 json으로 변환해서 브라우저에게 던져줌
		return user;
	}
	
	// http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id : "+user.getId());
		System.out.println("username : "+ user.getUsername());
		System.out.println("password : "+ user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
