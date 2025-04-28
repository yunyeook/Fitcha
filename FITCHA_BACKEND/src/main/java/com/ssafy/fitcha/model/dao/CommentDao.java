package com.ssafy.fitcha.model.dao;

import java.util.List;

import com.ssafy.fitcha.model.dto.Comment;

public interface CommentDao {

	//챌린지 댓글 삭제
	int deleteChallengeComment(int challengeBoardId);

	//챌린지 댓글목록 조회 
	List<Comment> selectChallnegeCommentList(int challengeBoardId);

	//챌린지 댓글 등록 
	int insertChallengeComment(Comment comment);

	//챌린지 댓글 수정
	int updateChallengeComment(Comment comment);

}
