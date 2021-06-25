package com.bm.allp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증이 안된 사용자들만 출입할 수 있는 경로를 "/auth/**"로 설정한다
// 그리고 그냥 주소가 '/'이면 index.jsp로 이동되게끔 허용하게 설정한다
// 그리고 static 폴더 아래에 있는 "/js/**", "/css/**", "/image/**"도 허용한다

@Controller
public class UserController {
	
	// http://localhost:8000/auth/joinForm
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}

	// http://localhost:8000/auth/loginForm
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	// http://localhost:8000/user/updateForm
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}

}
