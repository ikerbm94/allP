package com.bm.allp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bm.allp.model.Board;

// DAO의 역할
// 자동으로 bean 등록이 가능하기 때문에 @Repository는 생략가능하다
// extends JpaRepository<Board,Integer>을 통해서 Board 테이블에 대한 기본적인 CRUD 메서드들이 바로 만들어지는 것이다
// Board 테이블을 관리하는 Repository 만들기
// Integer는 Board 테이블의 Primary Key의 타입
public interface BoardRepository extends JpaRepository<Board,Integer> {
	
}