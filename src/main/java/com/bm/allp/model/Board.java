package com.bm.allp.model;

import java.sql.Timestamp;




import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // lombok
@NoArgsConstructor // lombok
@AllArgsConstructor // lombok
@Builder // lombok
@Entity // User 클래스가 MySQL에 테이블이 생성된다 (javax.persistence.Entity)
public class Board {

	@Id // Primary Key // 넘버가 자동으로 입력
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //auto_increment
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리와 <html>태그가 섞여서 들어가기 때문에 용량이 굉장히 커짐
	
	private int count; // 조회수
	
	@JoinColumn(name = "userId") // 컬럼명은 userId이다 (int) // Join을 해주는 컬럼값
	@ManyToOne // 여러 개의 Board가 하나의 User에 존속한다 (연관관계 설정) (FK 설정과 비슷함)
	private User user; // SQL Mapper에서는 객체를 저장할 수 없지만 ORM에서는 가능하다

	// 하나의 board에는 여러개의 reply가 존재한다
	// 여기서 mappedBy의 "board"는 Reply.java의 필드인 board를 의미한다
	// 따라서, 필요에 의해 해당 board에 존속한 reply 테이블들을 일시적으로 가져오게 된다
	// 즉, 실제로 DB에 저장되는 컬럼이 아닌 필요할때만 가져올 수 있게 설정해 놓은 일회용 값이 생성되는 방법이다
	// 기본적으로 ManyToOne의 fetch는 EAGER이다 (즉시로딩)
	// 기본적으로 OneToMany의 fetch는 LAZY이다 (지연로딩)
	// 게시글 창에서 댓글이 바로 보이게하려면 EAGER, 댓글 펼치기 등의 클릭으로 보이게하려면 LAZY
	// CascadeType을 REMOVE로 해놓으면 게시길을 삭제하면 관련 댓글도 삭제 되도록 설정하는 것이다
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"board"}) // 무한생성방지 // detail에서 board.reply로 댓글 생성하면 난리남 // 바로 reply로 댓글 생성하면 괜찮긴 함
	@OrderBy("id desc")
	private List<Reply> replies; // 해당 일회용 board 테이블에는 여러개의 reply 컬럼이 존재하기 때문에 List를 사용한다
	
	@CreationTimestamp // 시간을 자동으로 입력
	private Timestamp createDate; // java.sql.Timestamp
}
