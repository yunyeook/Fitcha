package com.ssafy.fitcha.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.fitcha.model.dao.CommentDao;
import com.ssafy.fitcha.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	private CommentDao commentDao;

	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	// 챌린지 댓글 조회
	@Override
	public List<Comment> getChallengeCommentList(int challengeBoardId) {
		return commentDao.selectChallengeCommentList(challengeBoardId);

	}

	// 챌린지 댓글 등록
	@Override
	public boolean registChallengeComment(int challengeBoardId, Comment comment) {
		comment.setBoardId(challengeBoardId);
		return 1 == commentDao.insertChallengeComment(comment);

	}

	// 챌린지 글의 댓글 삭제
	@Override
	public boolean deleteChallengeComment(int challengeBoardId, int challengeCommentId) {
		Comment comment = new Comment();
		comment.setBoardId(challengeBoardId);
		comment.setCommentId(challengeCommentId);
		return 1 == commentDao.deleteChallengeComment(comment);

	}

	// 챌린지 글의 댓글 수정
	@Override
	public boolean updateChallengeComment(int challengeBoardId, int challengeCommentId, Comment comment) {
		comment.setBoardId(challengeBoardId);
		comment.setCommentId(challengeCommentId);
		return 1 == commentDao.updateChallengeComment(comment);
	}

}
