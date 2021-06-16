package com.spring.demo.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.spring.demo.api.member.Member;
import com.spring.demo.api.member.MemberDetail;
import com.spring.demo.util.TokenUtils;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private static final String HEADER = "Authorization";
	private static final String TOKEN_PRE_TYPE = "BEARER";
	
	@Override
    public void onAuthenticationSuccess(final HttpServletRequest request, 
    									final HttpServletResponse response,
                                        final Authentication authentication) {
        final Member user = ((MemberDetail) authentication.getPrincipal()).getMember();
        final String token = TokenUtils.generateJwtToken(user);
        response.addHeader(HEADER, TOKEN_PRE_TYPE + " " + token);
    }


}
