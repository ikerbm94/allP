package com.bm.allp.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bm.allp.model.User;
import com.bm.allp.repository.UserRepository;

// Bean 등록을 위한 어노테이션
@Service
public class PrincipalDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	// Security가 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
	// password 부분 처리는 알아서 한다
	// username이 DB에 있는지만 확인해주면 됨
	// 그래서 이때 username 확인을 아래의 오버라이딩으로 진행한다
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// UserRepository의 findByUsername() 메서드의 리턴타입이 Optional이기 때문에 .orElseThrow로 명시해야 한다
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다" + username);
				});
		
		// principal 자체를 리턴하는게 아니라 UserDetails를 상속받아 임의로 만든 PrincipalDetail 리턴하는 이유는
		// Security의 세션저장소에 저장하기 위함이다
		return new PrincipalDetail(principal); 
	}

}
