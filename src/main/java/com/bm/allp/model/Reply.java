package com.bm.allp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Reply {
	
	@Id // Primary Key // 넘버가 자동으로 입력
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //auto_increment
	
	@Column(nullable = false, length = 200)
	private String content;

	@ManyToOne // 여러개의 reply가 하나의 user에 존속한다
	@JoinColumn(name="userId")// 컬럼명은 userId이다 (int)
	private User user;
	
	@ManyToOne // 여러 개의 reply가 하나의 board에 존속한다
	@JoinColumn(name="boardId")// 컬럼명은 boardId이다 (int)
	private Board board;
	
	@CreationTimestamp // 시간을 자동으로 입력
	private Timestamp createDate; // java.sql.Timestamp
	
}
