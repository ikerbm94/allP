package com.bm.allp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.allp.model.RoleType;
import com.bm.allp.model.User;
import com.bm.allp.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원가입(User userSave) {
		
		String rawPassword = userSave.getPassword(); // 비밀번호의 원문
		String encPassword = encoder.encode(rawPassword); // 비밀번호의 해쉬
		userSave.setPassword(encPassword); // 해쉬를 비밀번호로 저장
		
		// @RequestBody로 받아온 데이터들을 User 클래스의 필드로 저장한 후 회원가입을 진행하게 되는 과정이다
		// 그런데 id와 createDate 값은 알아서 들어가지만 role값은 따로 받지도 않았고 알아서 등록되지도 않기 때문에 직접 넣어주어야 한다
		userSave.setRole(RoleType.USER);
		
		// 만약 Exception 발생시에는 GlobalExceptionHandler가 의해 작동한다
		userRepository.save(userSave);
	}

	@Transactional
	public void 회원수정(int id, User userUpdate) {
		
		if (userUpdate.getEmail() == null) {
			
			String rawPassword = userUpdate.getPassword();
			String encPassword = encoder.encode(rawPassword);
			
			// 수정시에는 일단 기존의 User 정보를 영속화시킨 후 수정하면 자동으로 update문을 JPA가 해결해준다
			// 이때 findById로 가져온 객체는 Optional이기 때문에 orElseThrow를 해준다
			User userPersistence = userRepository.findById(id).orElseThrow(()->{
				return new IllegalArgumentException("회원 찾기 실패! 다음의 ID값을 찾을 수 없습니다 : " + id);
			});
			
			// set하고 메서드가 끝나는 순간 commit이 자동으로 이루어진다 (DB에 update문이 실행된다) (더티체킹)
			userPersistence.setPassword(encPassword);
			
		} else if (userUpdate.getPassword() == null) {
			
			String email = userUpdate.getEmail();
			
			// 수정시에는 일단 기존의 User 정보를 영속화시킨 후 수정하면 자동으로 update문을 JPA가 해결해준다
			// 이때 findById로 가져온 객체는 Optional이기 때문에 orElseThrow를 해준다
			User userPersistence = userRepository.findById(id).orElseThrow(()->{
				return new IllegalArgumentException("회원 찾기 실패! 다음의 ID값을 찾을 수 없습니다 : " + id);
			});
			
			// set하고 메서드가 끝나는 순간 commit이 자동으로 이루어진다 (DB에 update문이 실행된다) (더티체킹)
			userPersistence.setEmail(email);
			
		}
		
	}
	
	@Transactional
	public void 회원탈퇴(int id) {
		userRepository.deleteById(id);
	}

	@Transactional
	public boolean 아이디중복확인(String username) {
		return userRepository.existsByUsername(username);
	}
}