package com.spring.demo.config.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.demo.api.member.Member;
import com.spring.demo.exception.ReadValueNotFound;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public CustomAuthenticationFilter(final AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}
	
//	UserPasswordAuthenticationToken 토큰 발급.
	@Override
	public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException {
		UsernamePasswordAuthenticationToken authRequest = null;
		try {
			final Member member = new ObjectMapper().readValue(request.getInputStream(), Member.class);
			authRequest = new UsernamePasswordAuthenticationToken(member.getId(), member.getPassword());
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("IOException.");
			}
			throw new ReadValueNotFound();
		} catch (Exception e) {
			// TODO: handle exception
		}
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}
	
}
