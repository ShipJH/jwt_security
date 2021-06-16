package com.spring.demo.api.member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface MemberMapper {

	@Select("SELECT * FROM member")
	Member getMember();

	@Select("SELECT * FROM member WHERE id = #{id}")
	Member findMember(@Param("id") String id);


}
