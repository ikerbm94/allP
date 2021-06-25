package com.bm.allp.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bm.allp.model.User;

import lombok.Getter;

// 이렇게 하면, Security가 로그인 요청을 가로채서 로그인을 진행하고 완료되면 해당 로그인 정보들을
// UserDetails 타입의 오브젝트로 Security가 지닌 고유한 세션저장소에 저장된다
// 그리고 UserDetails를 implements로 상속하여 오버라이딩해서 PrincipalDetail라는 클래스 객체로 활용할 수 있게 되는 과정이다
@Getter
public class PrincipalDetail implements UserDetails{

	private User user; // 콤포지션 : 객체를 들고 있다는 뜻
	
	// 우리가 만든 User 테이블을 위한 필드를 통해 생성자를 만들지 않으면
	// 기본적으로 Security에서 제공하는 user라는 아이디와 콘솔창에 나오는 해쉬 비밀번호에 대한 계정정보만 나온다
	// 즉, 커스터마이징의 일부라고 생각하면 된다
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되었는지 리턴한다 (false: 만료됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨있는지 리턴한다 (false: 잠김)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되었는지 리턴한다 (false: 만료됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화 되었는지 리턴한다 (true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// 계정이 지니고있는 권한들을 리턴한다
	// Security의 내부 인터페이스인 GrantedAuthority가 들고있는, 즉 오버라이드 해야하는 메서드는 확인해보면 알겠지만 getAuthority()가 있다
	// 따라서 Override하여 인터페이스 객체를 하나 만들고 그것을 collectors에 add하는 과정이다
	// 권한에 대해서는 여러개가 존재할 수 있지만 일단은 하나만 있다
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		
		collectors.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return "ROLE_" + user.getRole(); // Spring에서 role 리턴시에 앞에 "ROLE_"를 붙이는건 규칙이다
			}
		});
		
		// 바로 위의 식을 람다식으로 표현한 것이다
		// collectors.add(()->{return "ROLE_" + user.getRole();});
		
		return collectors;
	}
	
}
