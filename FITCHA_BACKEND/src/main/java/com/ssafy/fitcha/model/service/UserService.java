package com.ssafy.fitcha.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.fitcha.model.dto.User;

public interface UserService {

	// 유저 회원가입
	boolean registUser(User user);

	// 유저 탈퇴
	boolean deleteUser(int userBoardId);

	// 유저 로그인
	User login(User user);

	
	// 유저 팔로우 
	boolean follow(String followerNickName, String followingNickName);

	// 유저 언팔로우 
	boolean unfollow(String followerNickName, String followingNickName);
	
	// 유저 팔로워 팔로잉 수 조회 
	Map<String, Integer> getFollowAllCount(int userBoardId);
	
	// 유저 팔로워 전체 조회 
	List<String> getFollowerAllList(String userNickName);
	
	// 유저 팔로잉 전체 조회 
	List<String> getFollowingAllList(String userNickName);


}
