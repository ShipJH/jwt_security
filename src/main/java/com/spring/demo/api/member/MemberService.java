package com.spring.demo.api.member;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

	private MemberMapper memberMapper;
	
	public Member getMember() {
		return memberMapper.getMember();
	}
	
	@Override
	public MemberDetail loadUserByUsername(String id) throws UsernameNotFoundException {
		Member member = memberMapper.findMember(id);
		return new MemberDetail(member, Collections.singleton(new SimpleGrantedAuthority(member.getRole().getVal())));
	}
	
}
