package com.ssafy.fitcha.security.oauth;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.ssafy.fitcha.model.dto.User;
import com.ssafy.fitcha.model.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

//회원가입으로 갈지 메인으로 갈지 정해야하 하므로 추가 

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	@Autowired
	private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @Override
    //내장된 메서드. 
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //OAuth2User객체에 맵형태로 정보가 담겨옴.
    	OAuth2User delegate = new DefaultOAuth2UserService().loadUser(userRequest);
        Map<String, Object> attributes = delegate.getAttributes();

		// 정보 꺼내오기 편하게 새로운 클래스에 저장.
        OAuth2UserInfo userInfo = new KakaoOAuth2UserInfoImpl(attributes);
        
		// db에 회원가입 정보 있는지 확인
        User dbUser = new User();
        dbUser.setUserId(userInfo.getEmail());
		dbUser = userService.login(dbUser);
		if (dbUser == null) {
			request.getSession().setAttribute("signupStatus", "true"); // 회원가입으로 이동
		} else {
			request.getSession().setAttribute("signupStatus", "false"); // 메인화면으로 이동
		}

		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")), attributes, "id");
    }
}
