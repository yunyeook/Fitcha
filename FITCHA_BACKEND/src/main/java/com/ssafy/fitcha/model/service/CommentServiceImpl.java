package com.ssafy.fitcha.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.fitcha.model.dao.CommentDao;
import com.ssafy.fitcha.model.dto.Comment;
import com.ssafy.fitcha.model.dto.User;

@Service
public class CommentServiceImpl implements CommentService {
	private CommentDao commentDao;

	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	// 챌린지 댓글 조회
	@Override
	public List<Comment> getChallengeCommentList(int challengeBoardId) {
		return commentDao.selectChallnegeCommentList(challengeBoardId);

	}

	// 챌린지 댓글 등록
	@Override
	public boolean registChallengeComment(int challengeBoardId, Comment comment) {
		comment.setBoardId(challengeBoardId);
		if (commentDao.insertChallengeComment(comment) != 0)
			return true;
		return false;
	}

	// 챌린지 글의 댓글 삭제
	@Override
	public boolean deleteChallengeComment(int challengeCommentId) {

		if (commentDao.deleteChallengeComment(challengeCommentId) != 0)
			return true;
		return false;

	}

	// 챌린지 글의 댓글 수정
	@Override
	public boolean updateChallengeComment(Comment comment) {
		if (commentDao.updateChallengeComment(comment) != 0)
			return true;
		return false;
	}

}
