package com.bm.allp.test;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

// 스프링이 com.bm.allp 패키지 이하를 스캔해서 모든 파일을 메모리에 new하는 것은 아니다
// 특정 어노테이션이 붙어있는 클래스 파일들을 new하여 컨테이너에서 관리한다

//@Controller는 사용자가 요청한 HTML 파일을 응답시켜주는 Annotation
//@RestController는 사용자가 요청한 Data를 응답시켜주는 Annotation

@RestController
public class AllpControllerTest {
	
	// http://localhost:8080/test/hello (application.yml 수정 전)
	// http://localhost:8000/allp/test/hello (application.yml 수정 후)
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}

}
