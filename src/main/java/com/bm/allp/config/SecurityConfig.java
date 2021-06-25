package com.bm.allp.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration	//마찬가지로 Config라는 설정파일들을 Spring Container에서 관리하기 위한 어노테이션
@EnableWebSecurity	//주소에 /auth/**를 확인하기 위해 모든 Mapping 주소가 일단 이곳을 거쳐가게 하기 위한 filter로 설정하는 어노테이션
@EnableGlobalMethodSecurity(prePostEnabled = true)	//권한 및 인증을 미리 체크하겠다는 의미의 어노테이션
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	// 해쉬 변환을 위해 Security에서 제공하는 BCryptPasswordEncoder 객체를 Bean으로 등록하자
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Security가 활성화 됨으로써 csrf 토큰이 없이 요청이 이루어지면 해당 요청을 막아버린다
		// 그렇기 때문에 지금 이 allP 프로젝트의 Ajax 코드에서 csrf 토큰에 대한 내용은 없기 때문에 disable로 해두는 것이다
		http.csrf().disable();
		http.authorizeRequests()												// 어떠한 Request가 들어왔을때
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**") // 주소가 auth로 시작한다면
				.permitAll()													// 모두다 허용한다
				.anyRequest()													// 하지만 그 외의 Request에 대해서는
				.authenticated()												// 인증이 필요하다 (Security의 기본 로그인폼 화면으로 이동 된다)
			.and()																// 그리고
				.formLogin()													// Security의 기본 로그인폼으로 맵핑되면
				.loginPage("/auth/loginForm")									// localhost:8000/auth/loginForm으로 맵핑된다
				.loginProcessingUrl("/auth/loginProc")							// 해당 주소로 오는 로그인 요청을 Security가 가로채서 대신 로그인 해준다
				.defaultSuccessUrl("/");										// 위의 로그인이 성공적으로 이루어졌다면 해당 주소로 맵핑한다
	}

}