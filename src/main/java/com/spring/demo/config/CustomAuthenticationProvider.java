package com.spring.demo.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.demo.api.member.MemberDetail;
import com.spring.demo.api.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final MemberService memberService; 
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		final UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication; 
		// AuthenticaionFilter에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함 
		final String id = token.getName();
		final String passwd = (String) token.getCredentials();
		
		final MemberDetail memberDetail = memberService.loadUserByUsername(id);
		
		if (passwordEncoder.matches(passwd, memberDetail.getPassword()) == false) {
			if (log.isDebugEnabled()) {
				log.debug("password Not matches");
			}
			log.info(passwd);
//			throw RuntimeException(); TODO: exception 처리c 
		}
		//TODO: 이제 맵퍼에서 조회하자~.
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	
	
}
