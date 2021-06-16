package com.spring.demo.api.member;

import java.time.LocalDateTime;

import com.spring.demo.domain.MemberRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

	private Long seq;
	private String id;
	private String password;
	private MemberRole role;
	private String name;
	private LocalDateTime registDatetime;
	private LocalDateTime updateDatetime;
	
}
