package com.spring.demo.api.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

	@Autowired MemberService memberService;
	
	@GetMapping(value = "/test")
	public Member get() {
		return memberService.getMember();
	}
}
