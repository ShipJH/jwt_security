package com.spring.demo.api.member;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

@Getter
@RequiredArgsConstructor
public class MemberDetail implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1877383559571051379L;
	private final boolean isEnabled = true;
	
	@Delegate
	private final Member member;
	private final Collection<? extends GrantedAuthority> authorities;
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	
	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getId();
	}

	
	@Override
	public boolean isAccountNonExpired() {
		return this.isEnabled;
	}


	@Override
	public boolean isAccountNonLocked() {
		return this.isEnabled;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return this.isEnabled;
	}


	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}
	
}
