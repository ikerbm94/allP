package com.bm.allp.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bm.allp.dto.ResponseDto;
import com.bm.allp.model.User;
import com.bm.allp.service.UserService;

// 데이터만 리턴해줄 것이기 때문에 RestController이다
@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	// auth를 통해 비회원도 허용한다
	// ajax의 기본 데이터 타입은 body 데이터이기 때문에 @RequestBody 사용
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> userSave(@RequestBody User user) {
		
		// System.out.println("UserApiController : 회원가입 userSave 호출");
		
		// 회원가입 메서드 실행, 리턴 값은 굳이 받지 않겠다
		// 그동안은 아래의 메서드 실행 후 리턴값으로 성공여부를 판단했다면
		// 지금은 에러가 난다면 어짜피 GlobalExceptionHandler에 의해 페이지가 이동되기 때문에 아래와같이 리턴값은 그냥 성공으로 두자 
		userService.회원가입(user);
		
		// HttpStatus.OK.value()는 숫자 200인데 성공적으로 실행되었다는 뜻이다
		// ajax에서 dataType을 json이라고 명시했기 때문에 아래의 리턴값의 형태는 json이다
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/api/user/update/{id}")
	public ResponseDto<Integer> userUpdate(@PathVariable int id, @RequestBody User user) {
		
		// System.out.println("UserApiController : 회원수정 userUpdate 호출");
		
		userService.회원수정(id, user);
		
		// HttpStatus.OK.value()는 숫자 200인데 성공적으로 실행되었다는 뜻이다
		// ajax에서 dataType을 json이라고 명시했기 때문에 아래의 리턴값의 형태는 json이다
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@GetMapping("/auth/api/user/usernameCheck/{username}")
	public int userUsernameCheck(@PathVariable String username) {
		
		System.out.println(username);

		if(userService.아이디중복확인(username)) {
			return 1;
		} else {
			return 0;
		}
		
	}
	
}