package com.bm.allp.model;

import java.sql.Timestamp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // lombok
@NoArgsConstructor // lombok
@AllArgsConstructor // lombok
@Builder // lombok
@Entity // User 클래스가 MySQL에 테이블이 생성된다 (javax.persistence.Entity)
//@DynamicInsert // insert를 할때 null인 값을 제외하고 insert 받는다 (즉, @ColumnDefault의 값이 주어진 필드는 디폴트 값으로 받을 수 있게 된다)
public class User {
	
	@Id // Primary Key // 넘버가 자동으로 입력
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다
	private int id; //시퀀스, auto_increment
	
	@Column(nullable = false, length = 30, unique = true)
	private String username;
	
	@Column(nullable = false, length = 100) // 123456 => 해쉬 (암호화) // 길이는 널럴하게
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	// 위에서 소개한 @DynamicInsert와 함께 사용하는 것이지만 일단 지금은 Enum 클래스를 사용하겠다
	// @ColumnDefault("'USER'") // 디폴트 값이 USER
	// private String role; // ADMIN, USER
	
	// Enum은 자바 클래스이기 때문에 DB에서는 RoleType이라는 타입을 인식하지 못하기 때문에 @Enumerated를 통해서 String이라는 사실을 알려줘야 한다
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@CreationTimestamp // 현재 시간이 자동으로 입력
	private Timestamp createDate; // java.sql.Timestamp

}
