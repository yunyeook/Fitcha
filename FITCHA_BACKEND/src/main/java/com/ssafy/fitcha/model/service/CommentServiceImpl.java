package com.ssafy.fitcha.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.fitcha.model.dao.CommentDao;

@Service
public class CommentServiceImpl implements CommentService {
	private CommentDao commentDao;
	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	//챌린지 글의 댓글 삭제
	@Override
	public void deleteChallengeComment(int challengeBoardId) {
		commentDao.deleteChallengeComment(challengeBoardId);
		
	}

}
