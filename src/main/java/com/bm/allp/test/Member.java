package com.bm.allp.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // getter, setter
@NoArgsConstructor // 기본 생성자
// @AllArgsConstructor // 생성자
public class Member {
	
	private int id;
	private String username;
	private String password;
	private String email;
	
	// 앞으로 @AllArgsConstructor을 대신해서 사용할 Annotation이다
	// 장점1 : 원하는 필드만 가지고 생성자를 만들 수 있다
	// 장점2 : 필드의 선언 순서에 대해서 자유롭다
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
	
}


