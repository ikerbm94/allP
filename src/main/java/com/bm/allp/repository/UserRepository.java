package com.bm.allp.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bm.allp.model.User;

// DAO의 역할
// 자동으로 bean 등록이 가능하기 때문에 @Repository는 생략가능하다
// extends JpaRepository<User,Integer>을 통해서 User 테이블의 기본적인 CRUD 메서드들이 바로 만들어지는 것이다
// User 테이블을 관리하는 Repository 만들기
// Integer는 User 테이블의 Primary Key의 타입
public interface UserRepository extends JpaRepository<User,Integer> {
	
	// SELECT * FROM USER WHERE username = 1?
	// 1?, 2?는 각각 첫 번째, 두 번째 파라미터를 의미한다
	// Optional은 username이 없을 수 있기 때문에 활용한다
	// 따라서 리턴값은 User 혹은 null
	Optional<User> findByUsername(String username);
	
	boolean existsByUsername(String username);

}