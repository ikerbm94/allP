package com.bm.allp.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
public class HttpControllerTest {
	
	////////////////////////////////////////////// GET //////////////////////////////////////////////

	// 인터넷 브라우저 요청은 무조건 get으로 요청할 수 밖에 없다.
	// select
	// http://localhost:8000/allp/http/get
	@GetMapping("/http/get")
	public String getTest() {
		return "get 요청";
	}
	
	// @RequestParam으로 주소를 통해 파라미터를 받을 수 있다.
	// http://localhost:8000/allp/http/getParam?id=123&password=testPassword
	@GetMapping("/http/getParam")
	public String getParamTest(@RequestParam int id, @RequestParam String password) {
		return "getParam 요청, id = " + id + ", password = " + password;
	}
	
	// 주소를 통해 파라미터들을 하나하나 받아올 수도 있지만 public인 Member 클래스의 필드로 받고 getter를 통해 꺼내올 수 있다.
	// get 방식에서는 주소에서 '?', '&' 등을 사용하여 주소를 통해 인자를 전달한다.
	// http://localhost:8000/allp/http/getMember?id=123&password=testPassword
	@GetMapping("/http/getMember")
	public String getMemberTest(Member m) {
		return "getMember 요청, id = " + m.getId() + ", password = " + m.getPassword(); 
	}
	
	////////////////////////////////////////////// POST //////////////////////////////////////////////
	
	// insert
	// http://localhost:8080/http/post (application.yml 수정 전)
	// http://localhost:8000/allp/http/post (application.yml 수정 후)
	@PostMapping("/http/post")
	public String postTest() {
		return "post 요청";
	}
	
	// 주소로 인자를 전달 받는 get과 달리 post는 <body> 태그의 데이터로 전달받는다 
	// http://localhost:8000/allp/http/postBody (인자는 <body> 태그로 전달한다)
	@PostMapping("/http/postBody")
	public String postBodyTest(@RequestBody String text) {
		return "postBody 요청, text = " + text;
	}
	
	// Member 클래스의 getter로 인자를 전달 받고 싶다면 <body> 태그안의 <form> 태그를 통해 전달받으면 된다.
	// http://localhost:8000/allp/http/postMember (인자는 <form> 태그로 전달한다)
	@PostMapping("/http/postMember")
	public String postMemberTest(Member m) {
		return "postMember 요청, id = " + m.getId() + ", password = " + m.getPassword();
	}
	
	// JSON 형태의 데이터를 보낼때도 반드시 Member 클래스로 받아서 활용하자
	// <form> 태그로 전달 받을때와는 달리 @RequestBody가 필요하다
	// http://localhost:8000/allp/http/postMemberJSON (인자는 JSON 형태로 전달한다)
	@PostMapping("/http/postMemberJSON")
	public String postMemberJSONTest(@RequestBody Member m) {
		return "postMemberJSON 요청, id = " + m.getId() + ", password = " + m.getPassword();
	}
	
	////////////////////////////////////////////// PUT //////////////////////////////////////////////
	
	// update
	// http://localhost:8000/allp/http/put
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}
	
	// post와 마찬가지로 <body> 태그의 데이터들을 전달받는다.
	// http://localhost:8000/allp/http/putMemberJSON
	@PutMapping("/http/putMemberJSON")
	public String putMemberJSONTest(@RequestBody Member m) {
		return "putMemberJSON 요청, id = " + m.getId() + ", username = " + m.getUsername() +
				 ", password = " + m.getPassword() + ", email" + m.getEmail();
	}
	
	////////////////////////////////////////////// DELETE //////////////////////////////////////////////
	
	// delete
	// http://localhost:8000/allp/http/delete
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
	// post와 마찬가지로 <body> 태그의 데이터들을 전달받는다.
	// http://localhost:8000/allp/http/deleteBody
	@DeleteMapping("/http/deleteMember")
	public String deleteBody(Member m) {
		return "deleteBody 요청, id = " + m.getId();
	}
	

	////////////////////////////////////////////// Lombok //////////////////////////////////////////////
	
	private static final String TAG = "HttpControllerTest : ";
	
	// @AllArgsConstructor를 사용하여 생성자를 만든 예시이다
	// http://localhost:8000/allp/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = new Member(123, "testUsername", "testPassword", "testEmail");
		System.out.println(TAG + "getter : " + m.getId());
		// @Data
		m.setId(12345);
		System.out.println(TAG + "setter : " + m.getId());
		return "lombok test 완료";
	}
	
	// 바로 위의 예시를 Builder로 바꾸어 사용한 예시이다
	// 주소는 http://localhost:8000/allp/http/lombokBuilder
	@GetMapping("/http/lombokBuilder")
	public String lombokBuilderTest() {
		Member m = Member.builder().username("testUsername").email("testEmail").password("testPassword").build();
		System.out.println(TAG + "getter : " + m.getUsername());
		// @Data
		m.setUsername("testUsernameBuilder");
		System.out.println(TAG + "setter : " + m.getUsername());
		return "lombokBuilder test 완료";
	}
	
}
