package com.ssafy.fitcha.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.fitcha.model.dao.UserDao;
import com.ssafy.fitcha.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	// 생성자 의존성 주입
	private final UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	// 유저 회원가입
	@Override
	public boolean registUser(User user) {

		return 1 == userDao.insertUser(user);
	}

	// 유저 탈퇴
	@Override
	public boolean deleteUser(int userBoardId) {
		return 1 == userDao.deleteUser(userBoardId);
	}

	// 유저 로그인
	@Override
	public User login(User user) {
		// 로그인 요청 받은 id와 일치한 registedUser 반환
		// 일치한게 없다면 null이다.
		User registedUser = userDao.selectRegistedUser(user);
		if (registedUser != null) {
			return registedUser;
		}
		return null;
	}

}
