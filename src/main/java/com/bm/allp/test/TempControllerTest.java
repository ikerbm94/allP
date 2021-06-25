package com.bm.allp.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	// (현재는 application.yml의 prefix와 suffix를 설정했기 때문에 제대로 동작하지 않는다)
	// application.yml에서 prefix와 suffix로 경로 변경을 하지 않았을때
	// 파일리턴 기본경로 : src/main/resources/static
	// 리턴명 : /home.html
	// 최종 경로 : src/main/resources/static/home.html
	// http://localhost:8000/allp/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		return "/home.html";
	}
	
}
